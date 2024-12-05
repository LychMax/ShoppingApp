<template>
  <div>
    <h1>Shopping Lists</h1>
    <ul>
      <li v-for="list in shoppingLists" :key="list.id">
        {{ list.name }}
        <button @click="editList(list.id)">Edit</button>
        <button @click="deleteList(list.id)">Delete</button>
      </li>
    </ul>
    <div>
      <h2>Add Shopping List</h2>
      <input v-model="newList.name" placeholder="Name" />
      <button @click="addList">Add</button>
    </div>
  </div>
</template>

<script>
import apiClient from '../services/api';

export default {
  data() {
    return {
      shoppingLists: [],
      newList: {
        name: '',
      },
    };
  },
  methods: {
    async fetchLists() {
      try {
        const response = await apiClient.get('/shopping-lists');
        this.shoppingLists = response.data;
      } catch (error) {
        console.error('Error fetching shopping lists:', error);
      }
    },
    async addList() {
      try {
        await apiClient.post('/shopping-lists', this.newList);
        this.newList = { name: '' };
        this.fetchLists();
      } catch (error) {
        console.error('Error adding shopping list:', error);
      }
    },
    async editList(id) {
      const newName = prompt('Enter new name:');
      if (newName) {
        try {
          await apiClient.put(`/shopping-lists/${id}`, { name: newName });
          this.fetchLists();
        } catch (error) {
          console.error('Error editing shopping list:', error);
        }
      }
    },
    async deleteList(id) {
      try {
        await apiClient.delete(`/shopping-lists/${id}`);
        this.fetchLists();
      } catch (error) {
        console.error('Error deleting shopping list:', error);
      }
    },
  },
  created() {
    this.fetchLists();
  },
};
</script>