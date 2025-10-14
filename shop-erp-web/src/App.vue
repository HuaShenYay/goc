<template>
  <div id="app">
    <router-view/>
    <AiAssistant/>
  </div>
</template>

<script setup>
import AiAssistant from '@/components/AiAssistant.vue'
</script>

<style scoped>
#app {
  min-height: 100vh;
}
</style>