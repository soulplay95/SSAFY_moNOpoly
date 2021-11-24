<template>
  <div>
    <base-header class="pb-6 pb-8 pt-5 pt-md-8">
    </base-header>
    <b-container fluid class="mt--7">
      <b-row class="justify-content-center">
        <b-col lg="12">
          <card header-classes="bg-transparent " class="backgroundCard" >
            <h3 slot="header" class="mb-0 noticeBoardTitle">공지사항</h3>
            <div class=" noticeBoardControlContainer">
              <b-form-select
                v-model="search_option"
                :options="search_options"
                class="noticeBoardSearchSelect "
              ></b-form-select>
              <b-form-input
                v-model="serach_keyword"
                id="input-small"
                class="noticeBoardSearchInput"
                placeholder="검색어를 입력하세요."
              ></b-form-input>
              <b-button class=" noticeBoardSearchBtn squared"  @click="onClickSearch">검색</b-button>
              <b-button v-if="isAdmin" class="noticeBoardCreateBtn" squared to="/noticeCreate">글쓰기</b-button>
            </div>

            <div class ="noticeBoardContents ">
              <b-table
                :items="notices"
                :fields="fields"
                head-variant="light"
                :per-page="perPage"
                :current-page="currentPage"
                small
              >
                <template #cell(title)="data">
                  <router-link :to="{path:'/noticeDetail/' + data.item.id, params:{id : data.item.id}}">
                    {{data.item.title}}
                  </router-link>
                </template>
              </b-table>
            </div>

            <div class="noticeBoardBottomContainer">
              <b-pagination class="noticeBoardPagination"
                v-model="currentPage"
                :total-rows="rows"
                :per-page="perPage"
                aria-controls="my-table"
                align="center"
                use-router
                :link-gen="linkGen"
              ></b-pagination>
            </div>
          </card>
        </b-col>
      </b-row>
    </b-container>

  </div>
</template>
<script>

  export default {
    watch :{

      getUserId(val)
      {
        this.getAdminId()
      }

    },
    methods: {
      getAdminId(){

        this.$axios.get(this.$baseUrl+"admin/"+this.getLibId)
        .then(res => {
          if(res.data[0].id == this.$store.getters['user/getUserId'])
          {
            this.isAdmin = true
          }
          else
          {
            this.isAdmin = false
          }
        })
        .catch(err => {
          this.isAdmin = false
        })
      },
      linkGen(pageNum) {
        return pageNum === 1 ? '?' : `?page=${pageNum}`
      },
      sortNotices(notices) {
        let sortedNotices = []

        sortedNotices = notices.reduce((acc, cur, idx) => {
          if(cur.isHead==false) {
            acc.unshift(cur);
          }
          return acc;
        }, sortedNotices)

        sortedNotices = notices.reduce((acc, cur, idx) => {
          if(cur.isHead==true) {
            cur._rowVariant = 'light'
            // primary secondary success warning danger info light dark
            acc.unshift(cur);
          }
          return acc;
        }, sortedNotices)
        return sortedNotices;
      },
      onClickSearch(event) {
        // console.log('key=', this.search_option, ' word=', this.serach_keyword)
        if(this.serach_keyword==''){
          alert("검색어가 없습니다.")
          this.$axios.get(this.$baseUrl +'notice/list/'+this.$store.getters['library/getLibraryId'])
      .then(res => {
            this.notices = [];
            res.data.forEach(element => {
              this.notices.push(element)
            })
            this.notices = this.sortNotices(this.notices)
          })
        }
        else {
          var searchParams = new URLSearchParams()
          searchParams.append('key', this.search_option)
          searchParams.append('word', this.serach_keyword)

          this.$axios.get(this.$baseUrl +'notice/list/', {params: searchParams})
          .then(res => {
            this.notices = [];
            res.data.forEach(element => {
              this.notices.push(element)
            })
            this.notices = this.sortNotices(this.notices)
          })

        }
      }
    },
    mounted() {
      var libId = this.$store.getters['library/getLibraryId']
      this.getAdminId()
    },
    data() {
      return {
        notices: [],
        user: {
          userType: '',
        },
        fields: [
          {label: '번호', key:'id', tdClass: 'noticeId', thClass: 'text-center w10'},
          {label: '제목', key:'title', tdClass: '', thClass: 'text-center'},
          {label: '조회수', key:'hitCnt', tdClass: 'createdDate', thClass: 'text-center w20'}
        ],
        perPage: 7,
        currentPage: 1,
        search_option: 'all',
        serach_keyword: '',
        search_options: [
          { value: 'all', text: '전체' },
          { value: 'title', text: '제목' },
          { value: 'content', text: '내용' }
        ],
        isAdmin : false

      }
    },
    computed: {
      rows() {
        return this.notices.length
      },
      getLibId(){
        return this.$store.getters['library/getLibraryId']
      },
      getUserId(){
        return this.$store.getters["user/getUserId"]
      }
    },
    created() {
      this.$axios.get(this.$baseUrl +'notice/list/'+this.$store.getters['library/getLibraryId'])
      .then(res => {
        this.notices = [];
        res.data.forEach(element => {
          this.notices.push(element)
        })
        this.notices = this.sortNotices(this.notices)
      })
    }
  };
</script>

<style>


.backgroundCard {
  height: 75vh;
}

div.card-body {
  height: 730px;
  position: relative;
}

.noticeBoardTitle {
  font-size: 1.5em;
}

.noticeBoardControlContainer {
  display: flex;
  float : right;
  margin-bottom: 1rem;
}

.noticeBoardSearchSelect {
  width: 70px;
  margin-right: 7px;
}

.noticeBoardSearchInput {
  width: 180px;
  margin-right: 7px;
}

.noticeBoardSearchBtn {
  width: 80px;
  margin-right: 17px;
}

.noticeBoardCreateBtn {
  width: 100px;
}

.noticeBoardContents {
  margin-bottom: 2rem;
}

/* table.b-table thead  */
th.w10{ text-align: center; width: 10%; }
th.w20{ width: 20%; }

td.noticeId {
  text-align: center;
}

td.createdDate  {
  text-align: center;
}

</style>
