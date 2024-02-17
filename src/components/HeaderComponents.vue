<template>
    <el-header style="line-height: 60px;display: flex">

        <div style="flex: 1;font-size: 18px;">
                    <span :class="collapseBtnClass" style="cursor: pointer"
                          @click="collapse">
                    </span>

            <el-breadcrumb separator="/" style="display: inline-block;margin-left: 10px">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item>{{ currentPathName }}</el-breadcrumb-item>

            </el-breadcrumb>


        </div>

        <el-dropdown style="width: 100px;cursor: pointer;text-align:right ">
            <div style="display: inline-block">

                <img :src="userView.avatar"
                     alt=""
                style="width: 30px;border-radius: 50%;position: relative;top: 10px;right: 5px">
            <span >{{ userView.nickname }}</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>

            </div>
            <el-dropdown-menu slot="dropdown" style="width: 100px;text-align: center">
                <router-link to="/Person" style="font-size: 14px;padding: 5px 0;text-decoration: none">个人信息</router-link>
                <el-dropdown-item  style="font-size: 14px;padding: 5px 0">
                <router-link to="/loginView" style="font-size: 14px">登录</router-link>

                <span style="text-decoration: none" @click="logout">退出</span>
                </el-dropdown-item>

            </el-dropdown-menu>
        </el-dropdown>
    </el-header>
</template>

<script>

export default {
    name: "HeaderComponents",
    props: {
        collapseBtnClass: String,
        collapse: Boolean,
        userView: Object
    },
    computed: {
        currentPathName() {
            return this.$store.state.currentPathName;
        }
    },
    data(){
        return{
        //    userView:localStorage.getItem("userView")?JSON.parse(localStorage.getItem("userView")):{}
        }
    },
    watch: {
        currentPathName(newVal) {
            console.log(newVal)
        }
    },
    methods: {
        logout() {
            this.$router.push("/loginView")
            localStorage.removeItem("userView")
            this.$message.success("退出成功")
        }
    }


}
</script>

<style scoped>

</style>