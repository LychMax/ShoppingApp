<script setup>
import ShoppingList from "./ShoppingList.vue";
import ShoppingCreateForm from "./ShoppingCreateForm.vue";
import { onMounted, ref } from "vue";
import apiClient from "../services/api.js";

const shoppingLists = ref([]);

const getShoppingList = async () => {
    const response = await apiClient.get("/shopping-lists");
    shoppingLists.value = response.data;
};

onMounted(async () => {
    await getShoppingList();
    console.log(shoppingLists.value);
});
</script>

<template>
    <div class="app-container">
        <h1>Создайте и управляйте своими покупками!</h1>
        <ShoppingList :data="shoppingLists" />
        <ShoppingCreateForm @create="getShoppingList" />
    </div>
    <div class="logo-container">
        <img src="/Users/maksimlych/Desktop/курсач/shop/src/assets/images/logo.png" alt="Logo" class="logo" />
    </div>
</template>

<style scoped>
body {
    background-color: #121212; 
    color: #e0e0e0; 
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
}

.app-container {
    max-width: 800px;
    margin: 0 auto;
    padding: 20px;
}

h1 {
    text-align: center;
    color: #646cff;
    margin-bottom: 100px;
}

button {
    background-color: #0d4ba8;
    color: white;
    border: none;
    padding: 10px 15px;
    border-radius: 5px;
    cursor: pointer;
    margin: 5px 0;
    transition: background-color 0.3s ease;
}

button:hover {
    background-color: #646cff;
}

button:active {
    background-color: #0d47a1;
}

input[type="text"],
input[type="number"] {
    padding: 10px;
    margin: 5px 0;
    border: 1px solid #424242;
    border-radius: 5px;
    background-color: #1e1e1e;
    color: white;
    width: calc(100% - 22px);
    box-sizing: border-box;
}

input::placeholder {
    color: #9e9e9e;
}

.list {
    display: flex;
    flex-wrap: wrap;
    gap: 20px;
    justify-content: center;
    margin-top: 20px;
}

.card {
    background-color: #1e1e1e;
    padding: 5px;
    border-radius: 10px;
    color: #e0e0e0;
    width: 250px;
    text-align: center;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.5);
}

.card p {
    margin: 5px 0;
}

.router-link {
    display: inline-block;
    margin-top: 10px;
    color: #00bcd4;
    text-decoration: none;
    font-weight: bold;
}

.router-link:hover {
    text-decoration: underline;
}
.logo-container {
    position: fixed;
    top: 50px;
    left: 50px;
}

.logo {
    width: 150px;
    height: auto;
}
</style>
