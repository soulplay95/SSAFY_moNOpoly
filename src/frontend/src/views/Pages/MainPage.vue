<template>
  <div>
    <!-- Header -->
    <div class="header  py-7 py-lg-8 pt-lg-9 headerColor">
      <b-container>
        <div class="header-body text-center mb-7">
          <b-row class="justify-content-center">
            <b-col xl="5" lg="6" md="8" class="px-5">
              <h1 class="text-white">MOnoPOLY</h1>
              <p class="text-lead text-white">사석화 방지 시스템</p>
            </b-col>
          </b-row>
        </div>
      </b-container>
      <div class="separator separator-bottom separator-skew zindex-100">
        <svg x="0" y="0" viewBox="0 0 2560 100" preserveAspectRatio="none" version="1.1"
             xmlns="http://www.w3.org/2000/svg">
          <polygon class="fill-default" points="2560 0 2560 100 0 100"></polygon>
        </svg>
      </div>
    </div>
    <!-- Page content -->
   <b-container class="mt--8 pb-5" id="container">
      <b-row class="justify-content-center">
        <b-col lg="5" md="7">
          <b-card no-body class="bg-secondary border-0 mb-0">
            <b-card-header class="bg-transparent pb-5 text-center "  >
                도서관을 선택해주세요
            </b-card-header>
            <div class="text-center text-muted mb-4">
                <!-- <small>도서관을 선택해주세요</small> -->
              </div>
            <b-card-body class="px-lg-5 py-lg-5">
              <div>
                 <p>특별시/도</p>
                 <b-form-select v-model="selectedArea" :options="sidoOptions" @change="setGuGun">
                 </b-form-select>

                 <p>시/구/군</p>
                 <b-form-select v-model="selectedGuGun" :options="gugunOptions" @change="setLib"></b-form-select>
                 <p class="mt-3">도서관</p>
                 <b-form-select v-model="selectedLib" :options="libraryOptions"></b-form-select>
                <b-button pill variant="warning" class="mt-3" @click="moveTo">이동</b-button>
              </div>

            </b-card-body>
          </b-card>

        </b-col>
      </b-row>
    </b-container>
  </div>
</template>
<script>
  export default {
    mounted(){
      this.$store.commit('library/clearAll')
      this.$axios.get(this.$baseUrl + 'library/sidos')
      .then(res => {

        res.data.forEach(element => this.sidoOptions.push(element))
      })
      .catch(err=>{

        console.log(err)
      })


    },
    data() {
      return {

       selectedArea: null,
       selectedGuGun : null,
       selectedLib : null,
       sidoOptions:[
         { value: null, text: '시/도를 선택해주세요', disabled: true }

       ],
       gugunOptions :[ { value: null, text: '구/군을 선택해주세요', disabled: true }],
       libraryOptions : [{ value: null, text: '도서관을 선택해주세요', disabled: true }       ]

      }
    },
    methods: {
      setGuGun(){

        this.gugunOptions = [ { value: null, text: '구/군을 선택해주세요', disabled: true }]
        this.libraryOptions = [{ value: null, text: '도서관을 선택해주세요', disabled: true }]
        this.selectedGuGun = null
        this.selectedLib = null
        this.$axios.get(this.$baseUrl + 'library/guguns/'+this.selectedArea)
          .then(res => {

            res.data.forEach(element => this.gugunOptions.push(element))
          })
          .catch(err=>{

            console.log(err)
          })

      },
      setLib(){
        this.libraryOptions = [{ value: null, text: '도서관을 선택해주세요', disabled: true }]
        this.selectedLib = null
        this.$axios.get(this.$baseUrl+'library/'+this.selectedGuGun)
          .then(res => {

            res.data.forEach(element =>{

              if(this.selectedArea == element.sido)
              {
                 this.libraryOptions.push({text : element.name, value : element.id})
              }




              })

          })
          .catch(err=>{

            console.log(err)
          })

      },
      moveTo(){

        if(this.selectedLib == null || this.selectedGuGun == null || this.selectedArea == null)
        {

          alert("선택하지 않은 필드가 있습니다.")
          return
        }

        this.$store.commit('library/updateLibraryArea',this.selectedArea+' '+this.selectedGuGun)
        this.$store.commit('library/updateLibraryId',this.selectedLib)

        this.$axios.get(this.$baseUrl+'library/search/?id='+this.selectedLib)
        .then(res =>{

          this.$store.commit('library/updateSeatCount',res.data.seatCounts)
          this.$store.commit('library/updateLibraryName',res.data.name)

          this.$router.push('dashboard')

        })

      },
      onSubmit() {
        // this will be called only after form is valid. You can do api call here to login
      }
    }
  };
</script>
<style scoped>
.headerColor {
  background-color: #B2D8FD;
}

p{

  font-weight :bolder;
}
#container{
  width : 100%;
}
</style>
