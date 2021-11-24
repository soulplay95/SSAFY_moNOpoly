<template>
  <base-nav
    container-classes="container-fluid"
    class="navbar-top navbar-expand"
    :class="{'navbar-dark': type === 'default'}"
  >

    <a href="#" aria-current="page" class="h4 mb-0 text-white text-uppercase d-none d-lg-inline-block active router-link-active"> {{curLibName+' '+$route.name}} </a>
    <div v-if="$route.name !='profile'">
    <i v-if="checkContain()" class="ni ni-tag text-yellow ml-3 favorite" @click="removeLibList()"></i>
    <i v-else class="ni ni-tag text-white ml-3 favorite" @click="addLibList()"></i>
    </div>
    <!-- Navbar links -->
    <b-navbar-nav class="align-items-center ml-md-auto">
      <!-- This item dont have <b-nav-item> because item have data-action/data-target on tag <a>, wich we cant add -->
      <li class="nav-item d-sm-none">
        <a class="nav-link" href="#" data-action="search-show" data-target="#navbar-search-main">
          <i class="ni ni-zoom-split-in"></i>
        </a>
      </li>
    </b-navbar-nav>
    <b-navbar-nav class="align-items-center ml-auto ml-md-0">

      <b-nav-item v-if="isLogIn" @click="loginWithKakao">
               <i class="ni ni-key-25 mr-2"></i>
               <span class="nav-link-inner--text" >로그인</span>

           </b-nav-item>
           <b-nav-item v-if="!isLogIn">

               <router-link to="/profile" tag="span"><i class="ni ni-single-02 mr-2"></i></router-link>
               <router-link to="/profile" tag="span" active-class="nav-link-inner--text ml-1" >프로필</router-link>

           </b-nav-item>
           <b-nav-item v-if="!isLogIn" @click="logoutWithKakao">
               <i class="ni ni-button-power mr-2"></i>
               <span class="nav-link-inner--text"  >로그아웃</span>

           </b-nav-item>
    </b-navbar-nav>
  </base-nav>
</template>
<script>
import { CollapseTransition } from 'vue2-transitions';
import { BaseNav, Modal } from '@/components';
import store from '../../store'
import axios from 'axios'

export default {
  components: {
    CollapseTransition,
    BaseNav,
    Modal
  },
  props: {
    type: {
      type: String,
      default: 'default', // default|light
      description: 'Look of the dashboard navbar. Default (Green) or light (gray)'
    }
  },
  computed: {
    routeName() {
      const { name } = this.$route;
      return this.capitalizeFirstLetter(name);

    },
    getCurLibName()
    {

      return this.$store.getters['library/getLibraryName']
    },
    getCurLibArea()
    {

      return this.$store.getters['library/getLibraryArea']
    },
    getCurId()
    {
      return this.$store.getters['library/getLibraryId']
    },
    getCheckContain()
    {
      return this.checkContain
    },
    getRouteName()
    {

      return this.$route.name
    },
     getUserId(){

        return store.getters['user/getUserId']
      }

  },
  watch :{


    getCurLibName(val){
      this.curLibName = val
    },
    getCurLibArea(val)
    {
      this.curLibArea = val;
    },
    getCheckContain(val)
    {
      this.checkContain = val;
    },
    getCurId(val)
    {
      this.curLibId = val;
    },
    getRouteName(val)
    {
      if(val == "profile")
      {
        this.curLibName = ''
      }
      else
      {

        this.curLibName=this.$store.getters['library/getLibraryName']
      }
    },
    getUserId(val){

        if(store.getters['user/getUserId'] == null)
      {
        this.isLogIn= true
      }
      else
      {

        this.isLogIn=false
      }

      }



  },
  mounted()
  {

    this.curLib = {name : this.$store.getters['library/getLibraryName'], area : this.$store.getters['library/getLibraryArea']}
    this.curLibName = this.$store.getters['library/getLibraryName']
    this.curLibArea = this.$store.getters['library/getLibraryArea']
    this.curLibId = this.$store.getters['library/getLibraryId']
    if(this.$store.getters['user/getUserId'] == null)
      {
        this.isLogIn= true
      }
      else
      {

        this.isLogIn=false
      }

    if(this.getRouteName == 'profile')
    {
      this.curLibName = ''
    }

  },
  data() {
    return {
      activeNotifications: false,
      showMenu: false,
      searchModalVisible: false,
      searchQuery: '',
      curLibName : '',
      curLibArea :'',
      curLibId : 0,
      curLib : {},
      isLogIn : true,
      id : null,
      email : null,
      nickname : null,

    };
  },
  methods: {
    checkContain(){

      var idx = this.$store.getters['user/getUserLib'].findIndex(i => i.libraryId==this.curLibId)
      if(idx == -1)
      {
        return false
      }
      else
      {

        return true
      }
    },
    addLibList(){

      if(this.$store.getters['user/getUserId'] != null)
      {

        this.id = this.$store.getters['user/getUserId']
        this.$axios.post(this.$baseUrl+"bookmark", { "libraryId" : this.curLibId, "userId" : this.id })
        .then(res => {
          store.commit('user/clearUserLibrary')
          axios.get(this.$baseUrl+"bookmark/{userId}",{params:{userId : this.id}})
            .then(res =>{

              //라이브러리 id로 조회하기

              res.data.forEach(element => {
              store.commit('user/addUserLibrary',element)}
              )

            })
        })
        .catch(err =>{

        })


      }
      else{

        alert("로그인 후 이용할 수 있습니다.")
      }



    },
    removeLibList(name){
      if(this.$store.getters['user/getUserId'] != null)
      {
        var id = this.$store.getters['user/getUserId']
        var idx = store.getters['user/getUserLib'].findIndex(i => i.libraryId == this.curLibId)
        var idxId =  store.getters['user/getUserLib'][idx]["id"]
        axios.delete(this.$baseUrl+"bookmark/"+idxId)
        .then(function(){
          store.commit('user/clearUserLibrary')
          axios.get("https://i5a209.p.ssafy.io:12346/"+"bookmark/{userId}",{params:{userId : id}})
            .then(res =>{

              //라이브러리 id로 조회하기

              res.data.forEach(element => {
              store.commit('user/addUserLibrary',element)}
              )

            })



        })



      }
      else{

        alert("로그인 후 이용할 수 있습니다.")
      }



    },
    loginWithKakao() {

        Kakao.Auth.login({
        success: async function(authObj) {


        await Kakao.API.request({
          url: '/v2/user/me',
          success: function(response) {
            this.id = response.id
            this.email = response.kakao_account.email
            this.nickname = response.properties.nickname
            store.commit('user/updateId',response.id)
            store.commit('user/updateEmail',response.kakao_account.email)
            store.commit('user/updateName',response.properties.nickname)
            response.access_token = authObj.access_token

            axios.post("https://i5a209.p.ssafy.io:12346/" +"user/login",response)
            .then(res => {



              axios.get("https://i5a209.p.ssafy.io:12346/"+"bookmark/{userId}",{params:{userId : this.id}})
              .then(res =>{

                //라이브러리 id로 조회하기

                res.data.forEach(element => {
                store.commit('user/addUserLibrary',element)}
                )

            })

            }).catch(err => {

            })



          },
          fail: function(error) {

          },
        });


      },
      fail: function(err) {

      },
    })


    },
      logoutWithKakao() {
        if (!Kakao.Auth.getAccessToken())
        {
          return
        }
        Kakao.Auth.logout(function() {})
        alert("로그아웃 되었습니다.")
        store.commit('user/updateId',null)
        store.commit('user/updateEmail',null)
        store.commit('user/updateName',null)
        store.commit('user/clearUserLibrary')
        this.isLogIn=true;
        if(this.$route.name =='관리자 좌석배치')
        {
          this.$router.push('home')
        }
        else if (this.$route.name =='noticeCreate' || this.$route.name =='noticeEdit')
        {
          this.$router.go(-1)
        }



      },
    capitalizeFirstLetter(string) {
      return string.charAt(0).toUpperCase() + string.slice(1);
    },
    toggleNotificationDropDown() {
      this.activeNotifications = !this.activeNotifications;
    },
    closeDropDown() {
      this.activeNotifications = false;
    }
  }
};
</script>
<style scoped>

.favorite{

  cursor: pointer;
}

</style>
