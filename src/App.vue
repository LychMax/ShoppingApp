<script setup>
</script>

<template>
  <RouterView />
</template>

<style scoped>
body {
  margin: 0;
  font-family: Arial, sans-serif;
  background-color: #121212;
  color: #e0e0e0;
}

h1 {
  color: #00bcd4;
  text-align: center;
}

button {
  background-color: #1e88e5;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

button:hover {
  background-color: #1565c0;
}

a {
  color: #00bcd4;
  text-decoration: none;
}

a:hover {
  text-decoration: underline;
}
</style>
