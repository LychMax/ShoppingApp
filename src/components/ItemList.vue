<template>
  <div>
    <h1>Items</h1>
    <ul>
      <li v-for="item in items" :key="item.id">
        {{ item.name }} - {{ item.price }}
        <button @click="editItem(item.id)">Edit</button>
        <button @click="deleteItem(item.id)">Delete</button>
      </li>
    </ul>
    <div>
      <h2>Add Item</h2>
      <input v-model="newItem.name" placeholder="Name" />
      <input v-model="newItem.description" placeholder="Description" />
      <input v-model="newItem.price" placeholder="Price" type="number" />
      <button @click="addItem">Add</button>
    </div>
  </div>
</template>

<script>
import apiClient from '../services/api';

export default {
  data() {
    return {
      items: [],
      newItem: {
        name: '',
        description: '',
        price: 0,
      },
    };
  },
  methods: {
    async fetchItems() {
      try {
        const response = await apiClient.get('/items');
        this.items = response.data;
      } catch (error) {
        console.error('Error fetching items:', error);
      }
    },
    async addItem() {
      try {
        // Устанавливаем текущую дату в формате ISO
        const itemToSend = {
          ...this.newItem,
          createdAt: new Date().toISOString(),
        };

        await apiClient.post('/items', itemToSend);

        // Сброс формы после успешного создания
        this.newItem = { name: '', description: '', price: 0 };
        this.fetchItems();
      } catch (error) {
        console.error('Error adding item:', error);
      }
    },
    async editItem(id) {
      const newName = prompt('Enter new name:');
      const newDescription = prompt('Enter new description:');
      const newPrice = prompt('Enter new price:');

      if (newName && newDescription && newPrice) {
        try {
          await apiClient.put(`/items/${id}`, {
            name: newName,
            description: newDescription,
            price: parseFloat(newPrice),
            createdAt: new Date().toISOString(), // Устанавливаем обновленную дату
          });
          this.fetchItems();
        } catch (error) {
          console.error('Error editing item:', error);
        }
      }
    },
    async deleteItem(id) {
      try {
        await apiClient.delete(`/items/${id}`);
        this.fetchItems();
      } catch (error) {
        console.error('Error deleting item:', error);
      }
    },
  },
  created() {
    this.fetchItems();
  },
};
</script>
