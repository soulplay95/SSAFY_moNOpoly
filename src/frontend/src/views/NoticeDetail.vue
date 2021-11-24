<template>
  <div>
    <base-header class="pb-6 pb-8 pt-5 pt-md-8 ">
    </base-header>

    <b-container fluid class="mt--7">
      <card header-classes="bg-transparent"  class="backgroundCard">
        <h3 slot="header" class="mb-0 noticeDetailTitle">{{ notices.title }}</h3>
          <b-row class="justify-content-center ">
            <b-col class="info noticeDetailUserName">
              <pre>담당자 : {{ this.$store.getters['library/getLibraryName'].split(' ')[this.$store.getters['library/getLibraryName'].split(' ').length -1] }} {{ writerName }}  |  등록일 : {{notices.createdDate}}  |  조회 : {{notices.hitCnt}}</pre>
            </b-col>
          </b-row>

          <div class=" mt-3 noticeDetailContentBorder ">
              <br>
              <p class="noticeDetailContentTitle">{{ notices.title }}</p>
              <span class="noticeDetailContentContext" v-html="notices.content"></span>
              <br>
          </div>

          <div class="noticeDetailBtnContainer mt-4">
            <b-button class="" squared to="/noticeBoard">목록으로</b-button>
            <b-button v-if="user.userId===notices.userId" class="ml-3" squared >
              <router-link :to="{path:'/noticeEdit/' + this.notice_id, params:{}}">
                수정
              </router-link>
            </b-button>
          </div>
      </card>
    </b-container>

  </div>
</template>
<script>


  export default {
    methods: {
    },
    mounted() {
    },
    data() {
      return {
        notices: {},
        writerName :"",
        notice_id: this.$route.params.notice_id,
        user: {
          userId : 0,
        },
      }
    },
    computed : {

      getCurUserId(){

        return this.$store.getters['user/getUserId']
      }

    },
    watch : {


      getCurUserId(val)
      {
        this.user.userId = val
      }
    },
    beforeCreate(){

      this.$axios.put(this.$baseUrl+'notice/'+this.$route.params.notice_id)
      .then(res => {
        console.log(this.$route.params.notice_id)
        console.log(res)
      })

    },
    created() {
      this.notice_id = this.$route.params.notice_id
      this.user.userId = this.$store.getters['user/getUserId']
      this.$axios.get(this.$baseUrl + 'notice/' + this.notice_id).then(res =>{

        this.notices = res.data
        this.$axios.get(this.$baseUrl +'user/'+this.notices.userId)
        .then(res => {

          this.writerName = res.data.nickname
        })

      })


      // this.notices[this.notice_id].hits++;
    },
  };
</script>

<style>
.backgroundCard {
  height: 75vh;
}

.noticeDetailTitle {
  font-size: 1.5em;
}

.noticeDetailContentBorder {
  height: 450px;
  border-top:1px solid lightgrey;
  border-bottom:1px solid lightgrey;
}

.noticeDetailContentTitle {
  font-size: 2em;
  font: bold;
  text-align: center;
  margin-top: 50px;
  margin-bottom: 30px;
}

.noticeDetailContentContext {
  margin-left: 5%;
  margin-right: 5%;
  height: 500px;
  display: block;
  overflow: auto;
}

div.card-body {
  height: 900px;
}

.noticeDetailUserName {
  padding-right: 1px solid gray;
}

.noticeDetailBtnContainer {
  display: flex;
  justify-content: center;
}

@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap');

.noticeDetailFont12 {
  font-family: 'Nanum Gothic', sans-serif;
  font-size: 1.2em;
}

</style>
