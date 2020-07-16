<template>
    <div>
        <h2>로그인</h2>
        <form @submit.prevent="onSubmit(username, password)">
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
                msg: ''
            }
        },
        methods: {
            onSubmit(username, password) {
                this.$store.dispatch('LOGIN', {username, password})
                    .then(() => this.redirect())
                    .catch(({message}) => this.msg = message)
            },
            redirect() {
                const {search} = window.location

                if (search === ''){
                    console.log("없음")
                    this.$router.push('/')
                }else {
                    console.log("있음")
                   const tokens = search.replace(/^\?/, '').split('&')

                    const {returnPath} = tokens.reduce((qs, tkn) => {
                        const pair = tkn.split('=')
                        qs[pair[0]] = decodeURIComponent(pair[1])
                        return qs
                    }, {})
                    console.log(returnPath)
                    this.$router.push("/"+returnPath)
                }
            }
        }
    }
</script>

<style scoped>

</style>