<script setup>
import { onMounted, ref } from "vue";
import apiClient from "../services/api.js";

const props = defineProps(["data"]);

const deleteList = async (id, index) => {
  await apiClient.get(`/shopping-lists/remove-list/${id}`);
  props.data.splice(index, 1);
};
</script>

<template>
  <div>
    <h2>Ваши списки покупок:</h2>
    <div class="list">
      <div class="card" v-for="(item, index) in props.data" :key="item.id">
        <h3>{{ item.name }}</h3>
        <p>Количество товаров: {{ item.items.length }}</p>
        <router-link :to="{ name: 'ListDetail', params: { id: item.id } }">
          Открыть список
        </router-link>
        <button @click="deleteList(item.id, index)">Удалить</button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.list {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  justify-content: center;
}

.card {
  background-color: #1e1e1e;
  padding: 15px;
  border-radius: 10px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.5);
  width: 250px;
  text-align: center;
  color: #e0e0e0;
}

.card h3 {
  margin-bottom: 10px;
  color: #646cff;
}

button {
  margin-top: 10px;
}
</style>
