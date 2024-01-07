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

        <el-dropdown style="width: 70px;cursor: pointer">
            <span>czc</span>
            <i class="el-icon-arrow-down" style="margin-left: 5px"></i>
            <el-dropdown-menu slot="dropdown" style="width: 100px;text-align: center">
                <el-dropdown-item style="font-size: 14px;padding: 5px 0">个人信息</el-dropdown-item>
                <el-dropdown-item style="font-size: 14px">退出</el-dropdown-item>

            </el-dropdown-menu>
        </el-dropdown>
    </el-header>
</template>

<script>
export default {
    name: "HeaderComponents",
    props: {
        collapseBtnClass: String,
        collapse: Boolean
    },
    computed: {
        currentPathName() {
            return this.$store.state.currentPathName;
        }
    },
    watch: {
        currentPathName(newVal) {
            console.log(newVal)
        }
    }


}
</script>

<style scoped>

</style>