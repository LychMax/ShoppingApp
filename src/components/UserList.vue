<template>
  <div>
    <h1>Users</h1>
    <ul>
      <li v-for="user in users" :key="user.id">
        {{ user.username }}
        <button @click="editUser(user.id)">Edit</button>
        <button @click="deleteUser(user.id)">Delete</button>
      </li>
    </ul>
    <div>
      <h2>Add User</h2>
      <input v-model="newUser.username" placeholder="Username" />
      <input v-model="newUser.password" placeholder="Password" type="password" />
      <button @click="addUser">Add</button>
    </div>
  </div>
</template>

<script>
import apiClient from '../services/api';

export default {
  data() {
    return {
      users: [],
      newUser: {
        username: '',
        password: '',
      },
    };
  },
  methods: {
    async fetchUsers() {
      try {
        const response = await apiClient.get('/users');
        this.users = response.data;
      } catch (error) {
        console.error('Error fetching users:', error);
      }
    },
    async addUser() {
      try {
        await apiClient.post('/users', this.newUser);
        this.newUser = { username: '', password: '' };
        this.fetchUsers();
      } catch (error) {
        console.error('Error adding user:', error);
      }
    },
    async editUser(id) {
      const newUsername = prompt('Enter new username:');
      const newPassword = prompt('Enter new password:');
      if (newUsername && newPassword) {
        try {
          await apiClient.put(`/users/${id}`, { username: newUsername, password: newPassword });
          this.fetchUsers();
        } catch (error) {
          console.error('Error editing user:', error);
        }
      }
    },
    async deleteUser(id) {
      try {
        await apiClient.delete(`/users/${id}`);
        this.fetchUsers();
      } catch (error) {
        console.error('Error deleting user:', error);
      }
    },
  },
  created() {
    this.fetchUsers();
  },
};
</script>