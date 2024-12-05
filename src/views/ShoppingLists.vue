<template>
  <div>
    <h1>Shopping Lists</h1>
    <ShoppingList />
  </div>
</template>

<script>
import ShoppingList from '../components/ShoppingList.vue';

export default {
  components: {
    ShoppingList,
  },
};
</script>