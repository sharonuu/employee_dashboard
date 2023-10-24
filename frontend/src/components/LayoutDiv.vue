<template>
  <HeaderNav> </HeaderNav>
  <div class="container">
    
    <WidgetList></WidgetList>
    <slot></slot>
  </div>
</template>

<script>
import HeaderNav from "./HeaderNav.vue"
import WidgetList from "./WidgetList.vue"
export default {
  name: "LayoutDiv",
  components: {
    HeaderNav,
    WidgetList
  }
};
</script>
