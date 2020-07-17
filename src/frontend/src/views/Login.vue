<template>
    <div>
        <h2>로그인</h2>
        <form @submit.prevent="login(username, password)">
            <input type="text" v-model="username" placeholder="아이디">
            <input type="password" v-model="password" placeholder="비밀번호">
            <input type="submit" value="로그인">
        </form>
        <p><i></i></p>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                username: '',
                password: '',
                msg: '',
                showDismissibleAlert: false
            }
        },
        methods: {
            login(username, password) {
                this.$store.dispatch('login', {username, password})
                    .then(() => this.$router.replace(this.$route.query.redirect || '/'))
                    .catch((error) => {
                        if (error.response.status === 401){
                            this.msg = "아이디나 비밀번호가 틀렸습니다."
                            this.makeToast('danger')
                        }
                    })
            },
            makeToast(variant) {
                this.$bvToast.toast('아이디나 비밀번호가 일치하지 않습니다.', {
                    title: '오류',
                    variant: variant,
                    solid: true
                })
            }
        }
    }
</script>

<style scoped>

</style>