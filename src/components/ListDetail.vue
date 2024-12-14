<script setup>
import { onMounted, ref } from "vue";
import { useRoute, useRouter } from "vue-router";
import apiClient from "../services/api.js";
import ItemCreateForm from "./ItemCreateForm.vue";

const route = useRoute();
const router = useRouter();

const listDetailData = ref(null);
const items = ref([]);
const totalPrice = ref(0);

const getAllItems = async () => {
    const response = await apiClient.get("/items");
    items.value = response.data;
};

const calculateTotalPrice = (itemId, quantity) => {
    const item = items.value.find(i => i.id === itemId);
    return item ? item.price * quantity : 0
};

const calculateTotal = () => {
    totalPrice.value = listDetailData.value.items.reduce((sum, item) => {
        return sum + calculateTotalPrice(item.itemId, item.quantity);
    }, 0).toFixed(2);
};

const addItem = async (listItem) => {
    const itemData = {
        shoppingListId: route.params.id,
        itemId: listItem.id,
        quantity: listItem.q,
    };

    const existingItem = listDetailData.value.items.find(item => item.itemId === listItem.id);
    if (existingItem) {
        existingItem.quantity = listItem.q;
        await updateItem(existingItem);
    } else {
        const response = await apiClient.post("/shopping-lists/add-item", itemData);
        listDetailData.value.items.push({
            ...itemData,
            itemName: listItem.name,
        });
    }

    calculateTotal();
};

const deleteItem = async (listItem, index) => {
    const itemData = {
        shoppingListId: route.params.id,
        itemId: listItem.itemId,
    };

    await apiClient.delete('/shopping-lists/remove-item', { data: itemData });
    listDetailData.value.items.splice(index, 1);
    calculateTotal();
};

const deleteFromItems = async (itemId) => {
    await apiClient.delete(`/items/${itemId}`);
    items.value = items.value.filter(item => item.id !== itemId);
};

const goToHomePage = () => {
    router.push("/");
};

onMounted(async () => {
    const response = await apiClient.get(`/shopping-lists/${route.params.id}`);
    listDetailData.value = response.data;
    await getAllItems();
    calculateTotal();
});
</script>

<template>
    <div v-if="listDetailData" class="shopping-list-container">
        <h1 class="title">{{ listDetailData.name }}</h1>
        
        <div class="list-items">
            <h2>Список товаров</h2>
            <div v-for="(item, index) in listDetailData.items" :key="index" class="item-card">
                <p>
                    <span class="item-name">{{ item.itemName }}</span> |
                    <span>Количество: {{ item.quantity }}</span> |
                    <span>Цена: {{ calculateTotalPrice(item.itemId, item.quantity) }} BYN</span>
                </p>
                <button class="btn delete-btn" @click="deleteItem(item, index)">Удалить</button>
            </div>
        </div>

        <div class="available-items">
            <h2>Все доступные товары</h2>
            <div v-for="listItem in items" :key="listItem.id" class="item-card">
                <p>
                    <span class="item-name">{{ listItem.name }}</span> |
                    <span>Цена: {{ listItem.price }} BYN</span>
                </p>
                <input type="number" v-model.number="listItem.q" min="1" class="quantity-input" placeholder="Количество" />
                <button class="btn add-btn" @click="addItem(listItem)">Добавить</button>
                <button class="btn delete-btn" @click="deleteFromItems(listItem.id)">Удалить</button>
            </div>
        </div>

        <div class="add-items">
            <h2>Добавить новый товар</h2>
            <ItemCreateForm @create="getAllItems" />
        </div>

        <div class="total-price">
            <h2>Общая сумма: <span>{{ totalPrice }} BYN</span></h2>
            <div class="action-buttons">
                <button class="btn back-btn" @click="goToHomePage">Вернуться</button>
            </div>
        </div>
    </div>
</template>

<style scoped>
.shopping-list-container {
    background: #1e1e1e;
    color: #fff;
    padding: 20px;
    border-radius: 8px;
    max-width: 800px;
    margin: 0 auto;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
}

.title {
    text-align: center;
    color: #6b63ff;
}

.action-buttons {
    display: flex;
    justify-content: flex-end, right;
    margin: 20px 0;
}

.btn {
    background: #6b63ff;
    color: white;
    border: none;
    padding: 10px 15px;
    margin: 5px;
    border-radius: 5px;
    cursor: pointer;
    font-size: 14px;
}

.btn.delete-btn {
    background: #e74c3c;
}

.btn:hover {
    opacity: 0.8;
}

.list-items, .available-items {
    margin: 20px 0;
}

.item-card {
    background: #2c2c2c;
    border-radius: 5px;
    margin: 10px 0;
    padding: 10px;
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.item-name {
    font-weight: bold;
    color: #6b63ff;
}

.quantity-input {
    padding: 5px;
    width: 50px;
    margin: 0 10px;
}

.total-price {
    text-align: center;
    font-size: 18px;
    margin-top: 20px;
    font-weight: bold;
}

h2 {
    color: #6b63ff;
    text-align: center;
}
</style>
