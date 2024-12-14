<script setup>
import { ref } from "vue";
import apiClient from "../services/api.js";

const emit = defineEmits(["create"]);

const itemName = ref("");
const itemDescription = ref("");
const itemPrice = ref("");

const createItemRequest = async () => {
    if (itemPrice.value < 0) {
        alert("Цена не может быть отрицательной");
        return;
    }

    await apiClient.post("/items", {
        name: itemName.value,
        description: itemDescription.value,
        price: itemPrice.value,
    });

    itemName.value = "";
    itemDescription.value = "";
    itemPrice.value = "";
    emit("create");
};
</script>

<template>
    <form @submit.prevent="createItemRequest" class="form-container">
        <input type="text" v-model="itemName" placeholder="Название" required class="input-field" />
        <input type="text" v-model="itemDescription" placeholder="Описание" class="input-field" />
        <input
            type="number"
            v-model="itemPrice"
            placeholder="Цена"
            min="0"
            step="0.01"
            required
            class="input-field"
        />
        <button type="submit" class="btn-submit">Создать</button>
    </form>
</template>

<style scoped>
.form-container {
    display: flex;
    flex-direction: column;
    gap: 15px;
    max-width: 400px;
    margin: 0 auto;
    padding: 20px;
    background: #2c2c2c;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
}

.input-field {
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    font-size: 14px;
    color: #ffffff;
}

.input-field:focus {
    outline: none;
    border-color: #6b63ff;
    box-shadow: 0 0 5px #6b63ff;
}

.btn-submit {
    background-color: #6b63ff;
    color: #fff;
    padding: 10px 15px;
    font-size: 16px;
    font-weight: bold;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background 0.3s ease;
}

.btn-submit:hover {
    background-color: #6b63ff;
}
</style>
