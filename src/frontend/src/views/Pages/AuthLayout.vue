<template>
  <div class="main-content bg-default">
    <base-nav
      v-model="showMenu"
      :transparent="true"
      menu-classes="justify-content-end"
      class="navbar-horizontal navbar-main navbar-top navbar-dark"
      expand="lg"
    >
      <div slot="brand" class="navbar-wrapper">
        <b-navbar-brand to="/">
          <!-- LOGO HERE -->
          <img src="img/brand/MOnoPOLY_Logo2.png" contain>
        </b-navbar-brand>
      </div>

     <template>
       <div class="navbar-collapse-header">
         <b-row>
           <b-col cols="6" class="collapse-brand">
             <router-link to="/">
               <img src="img/brand/green.png">
             </router-link>
           </b-col>
           <b-col cols="6" class="collapse-close">
             <button type="button" class="navbar-toggler" @click="showMenu = false">
               <span></span>
               <span></span>
             </button>
           </b-col>
         </b-row>
       </div>
         <b-navbar-nav  class="align-items-lg-center ml-lg-auto">

           <b-nav-item v-if="isLogIn" @click="loginWithKakao">
               <i class="ni ni-key-25 mr-2"></i>
               <span class="nav-link-inner--text" >로그인</span>

           </b-nav-item>
           <b-nav-item v-if="!isLogIn">

               <router-link to="Profile" tag="span"><i class="ni ni-single-02 mr-2"></i></router-link>
               <router-link to="Profile" tag="span" active-class="nav-link-inner--text ml-1" >프로필</router-link>

           </b-nav-item>
           <b-nav-item v-if="!isLogIn" @click="logoutWithKakao">
               <i class="ni ni-button-power mr-2"></i>
               <span class="nav-link-inner--text"  >로그아웃</span>

           </b-nav-item>
       </b-navbar-nav>
     </template>
    </base-nav>

    <div class="main-content">
      <zoom-center-transition
        :duration="pageTransitionDuration"
        mode="out-in"
      >
        <router-view></router-view>
      </zoom-center-transition>
    </div>

    <footer class="py-5" id="footer-main">
      <b-container >
        <b-row align-v="center" class="justify-content-xl-between">
          <b-col xl="6">
            <div class="copyright text-center text-xl-left text-muted">
              © {{year}} <a href="#" class="font-weight-bold ml-1" target="_blank">SSAFY A209</a>
            </div>
          </b-col>
          <b-col xl="6" class="col-xl-6">
            <b-nav  class="nav-footer justify-content-center justify-content-xl-end">
              <b-nav-item href="#" target="_blank" >
                SSAFY A209
              </b-nav-item>
              <b-nav-item href="#" target="_blank" >
                About Us
              </b-nav-item>
              <b-nav-item href="#"  target="_blank">
                Blog
              </b-nav-item>
              <b-nav-item href="#" target="_blank">
                License
              </b-nav-item>
            </b-nav>
          </b-col>
        </b-row>
      </b-container>
    </footer>
  </div>
</template>
<script>


  import { BaseNav } from '@/components';
  import { ZoomCenterTransition } from 'vue2-transitions';
  import store from '../../store'
  import axios from 'axios'

  export default {
    components: {
      BaseNav,
      ZoomCenterTransition
    },
    props: {
      backgroundColor: {
        type: String,
        default: 'RED'
      }
    },
    mounted(){
      if(store.getters['user/getUserId'] == null)
      {
        this.isLogIn= true
      }
      else
      {

        this.isLogIn=false
      }


    },
    data() {
      return {
        showMenu: false,
        menuTransitionDura1tion: 250,
        pageTransitionDuration: 200,
        year: new Date().getFullYear(),
        pageClass: 'login-page',
        id : null,
        email : null,
        nickname : null,
        isLogIn : true,

      };
    },
    computed: {
      title() {
        return `${this.$route.name} Page`;
      },
      getUserId(){

        return store.getters['user/getUserId']
      }
    },
    methods: {
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

      },
      toggleNavbar() {
        document.body.classList.toggle('nav-open');
        this.showMenu = !this.showMenu;
      },
      closeMenu() {
        document.body.classList.remove('nav-open');
        this.showMenu = false;
      },
      setBackgroundColor() {
        document.body.classList.add('bg-default');
      },
      removeBackgroundColor() {
        document.body.classList.remove('bg-default');
      },
      updateBackground() {
        if (!this.$route.meta.noBodyBackground) {
          this.setBackgroundColor();
        } else {
          this.removeBackgroundColor()
        }
      }
    },
    beforeDestroy() {
      this.removeBackgroundColor();
    },
    beforeRouteUpdate(to, from, next) {
      // Close the mobile menu first then transition to next page
      if (this.showMenu) {
        this.closeMenu();
        setTimeout(() => {
          next();
        }, this.menuTransitionDuration);
      } else {
        next();
      }
    },
    watch: {
      $route: {
        immediate: true,
        handler: function () {
          this.updateBackground()
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
    }
  };
</script>
<style lang="scss">
  $scaleSize: 0.8;
  @keyframes zoomIn8 {
    from {
      opacity: 0;
      transform: scale3d($scaleSize, $scaleSize, $scaleSize);
    }
    100% {
      opacity: 1;
    }
  }

  .main-content .zoomIn {
    animation-name: zoomIn8;
  }

  @keyframes zoomOut8 {
    from {
      opacity: 1;
    }
    to {
      opacity: 0;
      transform: scale3d($scaleSize, $scaleSize, $scaleSize);
    }
  }

  .main-content .zoomOut {
    animation-name: zoomOut8;
  }
</style>
