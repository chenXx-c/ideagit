<template>

    <div>
        <el-container style="min-height: 100vh">
            <el-aside :width="sideWidth+'px'" style="background-color: rgb(238, 241, 246)">

               <AsideComponents :isCollapse="isCollapse" :logoTextShow="logoTextShow"></AsideComponents>
            </el-aside>

            <el-container>


                <el-header style="border-bottom: 1px solid #ccc">

                <HeaderComponents :collapseBtnClass="collapseBtnClass" :collapse="collapse" :userView="userView"></HeaderComponents>
                </el-header>
                <el-main>

                    <router-view @refreshUser="getUser">


                    </router-view>


                </el-main>


            </el-container>
        </el-container>
    </div>


</template>

<script>


import AsideComponents from "@/components/AsideComponents.vue";
import HeaderComponents from "@/components/HeaderComponents.vue";

export default {
    components: {HeaderComponents, AsideComponents},
    name:'ManageView',
    data() {
        return {
            collapseBtnClass :'el-icon-s-unfold',
            sideWidth : 200,
            logoTextShow : true,
            userView:{}
        }
    },
    component: {
       AsideComponents,
        HeaderComponents
    },
    created() {

        this.getUser()
    },
    methods: {
       
        collapse() {//点击收缩按钮触发
            this.isCollapse = !this.isCollapse

            if (this.isCollapse) {
                this.sideWidth = 64
                this.collapseBtnClass = 'el-icon-s-unfold'
                this.logoTextShow = false
            } else {
                this.sideWidth = 200
                this.collapseBtnClass = 'el-icon-s-fold'
                this.logoTextShow = true
            }

        },
        getUser(){
            let username=localStorage.getItem("userView")?JSON.parse(localStorage.getItem("userView")).username : "12"
                //后台获取数据
            this.request.get("/user/username/"+ username).then(res=>{
               //重新赋值
                this.userView=res.data
            })
        }
    }
};
</script>
<style>
.headerBg {
    background-color: #ccc;
}


</style>





