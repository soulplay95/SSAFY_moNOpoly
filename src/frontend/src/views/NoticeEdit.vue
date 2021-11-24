<template>
  <div>
    <base-header class="pb-6 pb-8 pt-5 pt-md-8">
    </base-header>

    <b-container fluid class="mt--7">
      <card header-classes="bg-transparent"  class="backgroundCard">
        <b-row class="mt-3 mr-4">
          <b-col  class="noticeEditTypesColumn">
            <span class="noticeEditTypesLabel noticeEditFont12">분류</span>
          </b-col>
          <b-col>
            <b-form-select
              v-model="notice.isHead"
              :options="type_options"
              class="noticeEditTypeSelect"
            ></b-form-select>
          </b-col>
        </b-row>

        <b-row class="mt-3 mr-4">
          <b-col  class="noticeEditTypesColumn">
            <span class="noticeEditTypesLabel noticeEditFont12">제목</span>
          </b-col>
          <b-col>
            <b-form-input
              v-model="notice.title"
              class="noticeEditTitle mr-5"
            ></b-form-input>
          </b-col>
        </b-row>

        <b-row class="mt-3 mr-4">
          <b-col class="noticeEditTypesColumn">
            <span class="noticeEditTypesLabel noticeEditFont12">작성자</span>
          </b-col>
          <b-col>
            <span class="noticeEditWriter noticeEditFont12">{{ this.$store.getters['library/getLibraryName'].split(' ')[this.$store.getters['library/getLibraryName'].split(' ').length -1] }} {{ writerName }}</span>
          </b-col>
        </b-row>

        <!-- <b-row class="mt-3 mr-4">
          <b-col class="noticeEditTypesColumn">
            <span class="noticeEditTypesLabel noticeEditFont12">첨부파일</span>
          </b-col>
          <b-col>
            <b-form-file multiple>
            <template slot="file-name" slot-scope="{ names }" class="justify-content-center">
              <b-badge variant="light">{{ names[0] }}</b-badge>
              <b-badge v-if="names.length > 1" variant="light" class="ml-1">
                + {{ names.length - 1 }} More files
              </b-badge>
            </template>
            </b-form-file>
          </b-col>
        </b-row> -->

        <b-row class="mt-3  mr-4">
          <b-col class="noticeEditTypesColumn">
          </b-col>
          <b-col>
            <editor ref="editor" class="noticeEditEditorContext" v-bind:preHtml="preContext" @update-context="onChangeContext"/>

          </b-col>
        </b-row>

        <b-row class="mt-3 mr-4 justify-content-center">
          <div class="overflow-auto">
            <div class="noticeEditBtnContainer mt-4">
              <b-button class="" squared to="/noticeBoard">취소</b-button>
              <b-button class="ml-3" squared @click="onSubmit">
                  등록
              </b-button>
            </div>
          </div>
        </b-row>
      </card>
    </b-container>

  </div>
</template>
<script>
import Editor from '../components/Editor/Editor.vue'
  export default {
    components: {
      Editor,
    },
    computed : {
      getPreContext()
      {
        return this.preContext
      }

    },
    watch : {
      getPreContext(val)
      {


        this.preContext = val

      }

    },
    data() {
      return {
        writerName : '',
        notice_id: this.$route.params.notice_id,
        notice: {

        },
        preContext: 'asdf',
        type_options: [
          { value: false, text: '일반' },
          { value: true, text: '중요' }
        ],
        user: {
          userName:'',
          userId: 0,
        }

      };
    },
    created() {

      this.user.userId = this.$store.getters['user/getUserId']

      this.notice_id = this.$route.params.notice_id


      this.$axios.get(this.$baseUrl + 'notice/' + this.notice_id)
      .then(res =>{
      this.notice = res.data
      console.log(this.notice)
      this.preContext = this.notice.content
      this.$axios.get(this.$baseUrl +'user/'+this.notice.userId)
        .then(res2 => {

          this.writerName = res2.data.nickname
        })
      })



      // this.user.userName = this.$store.getters['user/getUserName']
    },
    methods: {
      refreshEditor(){

        this.$nextTick(()=>{

          console.log(this.$refs.editor)
        })


      },
      onSubmit(event) {
        event.preventDefault()
        if(this.notice.content.length <= 7)
        {

          alert("본문 내용이 없습니다.")
          return
        }
        else if(this.notice.title.length == 0)
        {

          alert("제목이 없습니다.")
          return
        }

        this.$axios.put(this.$baseUrl+'/notice',this.notice)
        .then(res=>{

          console.log(res)
        })


        this.$router.push({path:'/noticeDetail/' + this.notice_id, params:{}})
      },
      onChangeContext(html) {
        this.notice.content = html;

        // console.log(this.notice)
        // console.log("parent: ", html);
      }
    }
  };
</script>

<style>
.backgroundCard {
  height: 75vh;
}

div.card-body {
  height: 900px;
}

.noticeEditTypesLabel {
  margin-left: 40px;
}

.noticeEditTypesColumn {
  line-height: 46px;
  max-width: 150px;
}

.noticeEditTypeSelect {
  width: 100px;
  font-size: 1em;
}

.noticeEditTitle {
  font-size:1em;
  margin-right: 10%;
}

.noticeEditEditorContext {
  height: 350px;
  overflow: auto;
}

.noticeEditWriter {
  line-height: 46px;
}

.noticeEditBtnContainer {
  display: flex;
  justify-content: center;
}

.noticeEditFont12 {
  font-family: 'Nanum Gothic', sans-serif;
  font-size: 1.2em;
}


@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap');
</style>

