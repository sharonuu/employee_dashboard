<template>
  <HeaderNav> </HeaderNav>
  <div class="container">
    <WidgetList></WidgetList>
    <slot></slot>
  </div>
  <FooterInfo/>
</template>

<script>
import HeaderNav from "./HeaderNav.vue"
import WidgetList from "./WidgetList.vue"
import FooterInfo from "./FooterInfo.vue";
export default {
  name: "LayoutDiv",
  components: {
    HeaderNav,
    WidgetList,
    FooterInfo
  }
};
</script>
