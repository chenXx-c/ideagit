<template>
    <div class="wrapper">
        <div style="margin: 200px auto;background-color: #fff;width: 350px;height: 300px;padding: 20px;border-radius: 10px">
            <div style="margin: 20px 0;text-align: center;font-size: 24px"><b>登录</b>
                <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-user" v-model="userView.username"></el-input>
                <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password v-model="userView.password"></el-input>
                <div style="margin: 10px 0;text-align: right">

                    <el-button type="primary" size="small" autocomplete="off" >登录</el-button>
                    <el-button type="primary" size="small" autocomplete="off" >注册</el-button>

                </div>


            </div>

        </div>

    </div>
</template>

<script>

export default {
    name: "LoginView",

    data(){

        return{
            userView:{

            }
        }
    },
    methods:{
        login(){

        }
    }


}
</script>


<style scoped>

.wrapper {
    height: 100vh;
    background-image: linear-gradient(to bottom right, #FC466B, #3F5EFB);
    overflow: hidden;
}

</style>