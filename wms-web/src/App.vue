<template>
  <div id="app">
    <Index></Index>
  </div>
</template>

<script>

import {defineComponent} from "vue";
import Index from "@/components/Index.vue";

export default defineComponent({
  components: {
    Index
  }
})
</script>

<style>
  #app{
    height: 100%;
  }
</style>
