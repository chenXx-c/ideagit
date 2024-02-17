<template>
<!--    <el-aside width="sideWidth+'px" style="background-color: rgb(238, 241, 246)">-->

    <el-menu :default-openeds="['1', '3']" style="min-height: 100%;
                         overflow: hidden"
             background-color="rgb(48,65,86)"
             text-color="#fff"
             active-text-color="#ffd04b"
             :collapse-transition="false"
             :collapse="isCollapse"
             router
    >

        <div style="height: 60px;line-height: 60px;text-align: center">
            <img src="../assets/logo.png" alt=""
                 style="width: 20px;position: relative;top: 5px;margin-right: 5px">
            <b style="color: white" v-show="logoTextShow">后台管理系统</b>
        </div>
        <el-menu-item index="/">
            <template slot="title"><i class="el-icon-message"></i>

                <span slot="title">主页</span>
            </template>
        </el-menu-item>
        <el-submenu index="2">
            <template slot="title"><i class="el-icon-menu">

            </i><span slot="title">系统管理</span>
            </template>
           <el-menu-item index="/UserView">
               <i class="el-icon-s-custom"></i>
               <span slot="title">用户管理</span>
           </el-menu-item>
            <el-menu-item index="/File">
                <i class="el-icon-document"></i>
                <span slot="title">文件管理</span>
            </el-menu-item>
            <el-menu-item index="/RoleView">
                <i class="el-icon-document"></i>
                <span slot="title">角色管理</span>
            </el-menu-item>
            <el-menu-item index="/niganma">
                <i class="el-icon-document"></i>
                <span slot="title">你干嘛</span>
            </el-menu-item>
            <el-menu-item index="/Menu">
                <i class="el-icon-document"></i>
                <span slot="title">菜单管理</span>
            </el-menu-item>

        </el-submenu>



    </el-menu>
<!--        </el-aside>-->
</template>



<script>
export default {
    name: "AsideComponents",
    props:{
        isCollapse:Boolean,
        logoTextShow:Boolean
    }

}

</script>

<style scoped>


</style>
<script setup>
</script>