<template>
  <div id="app">
    <!--<div @click="go">点击</div>-->
    <div style="margin-bottom: 50px">
      <h1>信息浏览</h1>
      <!--<router-link to="/home">切换到home组件 </router-link>
      <router-link to="/all">切换到home组件 </router-link>-->
    </div>
    <router-view/>
    <!--<home></home>-->
  </div>
</template>

<script>

export default {
  name: 'App',
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
