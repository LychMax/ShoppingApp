<script setup>
import { ref } from "vue";
import apiClient from "../services/api.js";

const emit = defineEmits(["create"]);
const nameShoppingList = ref("");
const errorMessage = ref("");

const createShoppingListRequest = async () => {
  if (!nameShoppingList.value.trim()) {
    errorMessage.value = "Название списка не может быть пустым!";
    return;
  }

  const existingLists = await apiClient.get("/shopping-lists");
  const isDuplicate = existingLists.data.some(
    (list) => list.name.toLowerCase() === nameShoppingList.value.toLowerCase()
  );

  if (isDuplicate) {
    errorMessage.value = "Список с таким именем уже существует!";
    return;
  }

  await apiClient.post("/shopping-lists", { name: nameShoppingList.value });
  nameShoppingList.value = "";
  errorMessage.value = "";
  emit("create");
};
</script>


<template>
  <form @submit.prevent="createShoppingListRequest" class="form">
    <input
      type="text"
      v-model="nameShoppingList"
      placeholder="Введите название списка"
    />
    <button type="submit">Создать список</button>
  </form>
</template>

<style scoped>
.form {
  display: flex;
  justify-content: center;
  gap: 10px;
  margin-top: 20px;
}

input {
  padding: 10px;
  border: 1px solid #424242;
  border-radius: 15px;
  background-color: #1e1e1e;
  color: white;
}

button {
  padding: 10px 20px;
}
</style>
