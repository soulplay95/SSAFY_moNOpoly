<template>
    <div>
      <br>
      <div class ="box-free mr-3"></div> <p style = "display : inline">: 사용가능</p>
      <br>
      <div class="box-used mr-3">

      </div><p style = "display : inline">: 사용중</p>
            <br>
      <div class ="box-user mr-3"></div> <p style = "display : inline">: 사용자의 좌석</p>
        <div  style="margin-top:10px;" >
            <grid-layout :layout.sync="layout"
                         :col-num="20"
                         :row-height="30"
                         :is-draggable="false"
                         :is-resizable="false"
                         :vertical-compact="false"
                         :use-css-transforms="true"
            >
               <seatModalUser v-if="seatModalVisible" ref="seatModal" :seatData ="seatModalData" :index="dataidx" :show="seatModalVisible" @close="closeSeatModal" @closeReserve="showNoticeModal" @endUse="endUse" ></seatModalUser>
               <noticeRuleModal v-if="ruleModalVisible" @closeNotice="closeNoticeModal" :index="dataidx" @closeWithAccept="changeSeatState"></noticeRuleModal>
               <!-- <reserveModal v-if="reserveModalVisible" @closeReserve="closeReserveModal"></reserveModal> -->
               <grid-item v-for="(item,idx) in this.layout"
                           :key = "idx"
                           :x="item.x"
                           :y="item.y"
                           :w="item.w"
                           :h="item.h"
                           :i="item.i"

                :class="seatMyClass(idx)">
                <div class ="seatClickArea" @click="showSeatModal(item,idx)">

                    <span class="text" v-if="item.i != '출입문'">{{item.i}}</span>


                </div>

                </grid-item>

            </grid-layout>

          <div>
</div>
        </div>
    </div>
</template>

<script>
import { GridLayout, GridItem } from "vue-grid-layout"
import seatModalUser from "../components/Modal/SeatModalUser.vue"
import noticeRuleModal from "../components/Modal/NoticeRuleModal.vue"
import reserveModal from "../components/Modal/ReservationModal.vue"

export default {
    name : "moving",
    components: {
        GridLayout,
        GridItem,
        seatModalUser,
        noticeRuleModal,
        reserveModal,
    },
    mounted(){
      this.getSeatDataAPI()
      this.intervalFetchData()




    },
    destroyed(){

      clearInterval(this.interval)


    },
    data() {
        return {

            layout: [
            ],
            index: 6,
            colNum : 20,
            draggable : true,
            seatModalData :  {x:16, y :0,  w :4, h :4, i  :  "출입문" ,  used :false},
            seatModalVisible : false,
            ruleModalVisible : false,
            reserveModalVisible : false,
            dataidx : 0,
            interval : null,

        }
    },
    watch: {
        eventLog: function() {
            const eventsDiv = this.$refs.eventsDiv;
            eventsDiv.scrollTop = eventsDiv.scrollHeight;
        }
    },
    methods: {
        intervalFetchData(){

         this.interval = setInterval(()=>{

          this.getSeatDataAPI()

         },1000)

       },
        getSeatDataAPI(){


         this.$axios.get(this.$baseUrl+'seat/list/'+this.$store.getters['library/getLibraryId'])
         .then(res => {
          this.layout = []

           this.seats = res.data
           var minX=9999999;
           var minY=9999999;
           var maxX=0;
           var maxY=0;
           var isUpdated = true

            var feMaxX = 0;
            this.seats.forEach(element =>{
              if(element.feCoordinateX >feMaxX)
              {
                feMaxX = element.feCoordinateX
              }


            })
            this.colNum = feMaxX*3
            this.seats.sort(function (a,b){
              if(a.feCoordinateY == b.feCoordinateY)
              {
                return a.feCoordinateX - b.feCoordinateX
              }
              return a.feCoordinateY-b.feCoordinateY

            })
            this.seats.forEach((element,index)=>{
            if(element.frontState != 0)
            {

              this.layout.push(

              {
                x : element.feCoordinateX*2,
                y : element.feCoordinateY*2,
                w : 2,
                h : 2,
                i : index+1,
                used : true,
                id : element.userId,
                frontState : element.frontState,


              }



            )


            }
            else{
              this.layout.push(

              {
                x : element.feCoordinateX*2,
                y : element.feCoordinateY*2,
                w : 2,
                h : 2,
                i : index+1,
                used : false,
                id : null,
                frontState : element.frontState,



              }



            )



            }





          })
          this.index = this.layout.length




         }).catch(err => {


           alert("도서관 정보가 없습니다. 도서관 관리자에게 문의해주세요.")
           this.$router.push('home')
         })



        },
        endUse(idx){


          this.seats[idx].userId = null
          this.seats[idx].frontState = 0

          var obj = {
                      feCoordinateX : this.seats[idx].feCoordinateX ,
                      feCoordinateY : this.seats[idx].feCoordinateY,
                      frontState : this.seats[idx].frontState,
                      id : this.seats[idx].id,
                      userId : this.seats[idx].userId,
                      sectionId : this.$store.getters['library/getLibraryId']


          }

          this.$axios.put(this.$baseUrl + 'seat/fe', obj)
          .then(res => {


              this.layout[idx].id = null
              this.layout[idx].used = false
              alert("사용을 종료합니다.")

          })
          .catch(err => {


          })


        },
        closeReserveModal(){

          this.reserveModalVisible = false;
        },
        changeSeatState(idx){

          // this.reserveModalVisible = true;

          this.$axios.get(this.$baseUrl+'seat?userId='+this.$store.getters['user/getUserId'])
          .then(res => {


            alert("이미 사용중인 좌석이 있습니다.")


          })
          .catch(err=>{
            this.seats[idx].userId = this.$store.getters['user/getUserId']
            this.seats[idx].frontState = 1
          var obj = {
                      feCoordinateX : this.seats[idx].feCoordinateX ,
                      feCoordinateY : this.seats[idx].feCoordinateY,
                      frontState : this.seats[idx].frontState,
                      id : this.seats[idx].id,
                      userId : this.seats[idx].userId,
                      sectionId : this.$store.getters['library/getLibraryId']

          }
            this.$axios.put(this.$baseUrl + 'seat/fe', obj)
            .then(res => {

                this.layout[idx].id = this.$store.getters['user/getUserId']
                this.layout[idx].used = true
                alert("사용을 시작합니다.")

            })
            .catch(err => {


            })


          })

        },

        closeNoticeModal(){

          this.ruleModalVisible = false
        },

        showNoticeModal(){

          this.seatModalVisible = false;
          this.ruleModalVisible = true;


        },
        closeSeatModal(){

          this.seatModalVisible = false;


        },
        showSeatModal(item,idx){

          if(item.i != "출입문")
          {

             this.seatModalData = item
             this.dataidx = idx
          this.seatModalVisible = true;
          }


        },
        seatMyClass(idx){

          if(this.layout[idx].id==this.$store.getters['user/getUserId'] && this.$store.getters['user/getUserId'] != null)
          {
            return "userOwn"
          }
          else if(this.layout[idx].frontState != 0)
          {
            return "seatOccupied"
          }
          else{

            return "seat"
          }


      },
      onDrag(e) {
            e.target.style.transform = e.transform;
        },
        toggleDraggable() {
            this.draggable = !this.draggable;
        }
    }

}
</script>

<style scoped>
.vue-grid-layout {
    background: white;
}

.vue-grid-item .resizing {
    opacity: 0.9;
}
.vue-grid-item .static {
    background: #cce;
}
.vue-grid-item .text {
    font-size: 24px;
    text-align: center;
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    margin: auto;
    height: 100%;
    width: 100%;
}
.vue-grid-item .no-drag {
    height: 100%;
    width: 100%;
}
.vue-grid-item .minMax {
    font-size: 12px;
}
.vue-grid-item .add {
    cursor: pointer;
}
.vue-draggable-handle {
    position: absolute;
    width: 20px;
    height: 20px;
    top: 0;
    left: 0;
    background: url("data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' width='10' height='10'><circle cx='5' cy='5' r='5' fill='#999999'/></svg>") no-repeat;
    background-position: bottom right;
    padding: 0 8px 8px 0;
    background-repeat: no-repeat;
    background-origin: content-box;
    box-sizing: border-box;
    cursor: pointer;
}
.layoutJSON {
    background: #ddd;
    border: 1px solid black;
    margin-top: 10px;
    padding: 10px;
}
.eventsJSON {
    background: #ddd;
    border: 1px solid black;
    margin-top: 10px;
    padding: 10px;
    height: 100px;
    overflow-y: scroll;
}
.seat{
   background:skyblue;
    border: 1px solid black;


}

.userOwn{
   background : #b1fc03;
    border: 1px solid black;


}

.seatOccupied{
   background:rgb(235, 135, 135);
    border: 1px solid black;


}

.seatExit{
   background:skyblue;
    border: 1px solid black;

}

.seatClickArea{

  height : 100%;
  width : 100%;
  cursor: pointer;
}

.box-used{

  width : 20px;
  height: 20px;
  background-color  : rgb(235, 135, 135);
  border: 1px solid black;
  float:left

}

.box-free{

  width : 20px;
  height: 20px;
  background-color  : skyblue;
  border: 1px solid black;
  float:left

}

.box-user{

    width : 20px;
  height: 20px;
  background-color  : #b1fc03;
  border: 1px solid black;
  float:left

}

</style>
