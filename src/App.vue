<template>
  <div id="app">
    <nav>
      <router-link to="/">Home</router-link>
      <router-link to="/items">Items</router-link>
      <router-link to="/shopping-lists">Shopping Lists</router-link>
      <router-link to="/users">Users</router-link>
    </nav>
    <router-view />
  </div>
</template>

<style>
nav a {
  margin: 10px;
  text-decoration: none;
  color: blue;
}
</style>