<template>
    <div>
            <br>
      <div class ="box-free mr-3"></div> <p style = "display : inline">: 사용가능</p>
      <br>
      <div class="box-used mr-3"></div>  <p style = "display : inline">: 사용중</p>
      <br>
      <div class="box-monopoly mr-3"></div>  <p style = "display : inline">: 사석화감지</p>
      <br>
        <div  style="margin-top:10px;" >
             <button type="button" class="btn btn-info mb-3 renew" @click="seatRenew()">초기화</button>
            <!-- <button type="button" class="btn btn-info mb-3" @click="seatRemoveItem()">출입문 추가</button> -->
            <grid-layout :layout.sync="layout"
                         :col-num="colNum"
                         :row-height="30"
                         :is-draggable="true"
                         :is-resizable="true"
                         :vertical-compact="false"
                         :use-css-transforms="true"
            >
               <seatModalAdmin v-if="modalVisible" ref="Modal" :seatData ="modalData" :index="dataidx" :show="modalVisible" @close="closeModal" @updateOccupy="seatChangeState" ></seatModalAdmin>

               <grid-item v-for="(item,idx) in layout"
                           :key = "idx"
                           :x="item.x"
                           :y="item.y"
                           :w="item.w"
                           :h="item.h"
                           :i="item.i"

                :class="seatMyClass(idx)"

                >
                <div class ="seatClickArea">
                      <span class="text" @click.right.prevent="seatShowModal(item,idx)">{{item.seatNum}}</span>


                </div>

                </grid-item>
            </grid-layout>
        </div>
        <button type="button" class="btn btn-info mt-3 seatSave-Btn" @click="seatUpdateAll()">저장</button>
    </div>
</template>

<script>
import { GridLayout, GridItem } from "vue-grid-layout"
import seatModalAdmin from "./Modal/SeatModalAdmin.vue"
export default {
    name : "movingadmin",
    components: {
        GridLayout,
        GridItem,
        seatModalAdmin
    },
    mounted(){

        //  this.layout = JSON.parse(JSON.stringify(this.$store.getters['seat/getSeatData']))
        //  this.colNum = JSON.parse(JSON.stringify(this.$store.getters['seat/getColNum']))
        //  this.index = JSON.parse(JSON.stringify(this.$store.getters['seat/getIndex']))
         this.layout = []
         this.$axios.get(this.$baseUrl+'seat/list/'+this.$store.getters['library/getLibraryId'])
         .then(res => {

           this.seats = res.data
           var minX=9999999;
           var minY=9999999;
           var maxX=0;
           var maxY=0;
           var isUpdated = true
           this.seats.forEach(element => {

             if(element.feCoordinateX == null || element.feCoordinateY==null)
             {
               isUpdated = false
               return false
             }


           })

           if(isUpdated == false)
           {
             this.seats.forEach(element =>{

             if(minX >element.coordinateX)
             {
               minX = element.coordinateX
             }
             else if(maxX < element.coordinateX)
             {
               maxX = element.coordinateX
             }

             if(minY > element.coordinateY)
             {
               minY = element.coordinateY
             }
             else if(maxY<element.coordinateY)
             {
               maxY = element.coordinateY
             }




           })
          var feMaxX = 0;
          this.seats.forEach(element =>{
            element.feCoordinateX = parseInt(element.coordinateX/minX);
            element.feCoordinateY = parseInt(element.coordinateY/minY);
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

          // modalData :{x:16, y :0,  w :4, h :4, i  :  "출입문" ,  used :false},
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
                seatNum : element.id,
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
                seatNum : element.id,


              }



            )



            }





          })
          this.index = this.layout.length


          }
          else
          {

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
                seatNum : element.id,
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
                seatNum : element.id,
                frontState : element.frontState,


              }



            )



            }





          })
          this.index = this.layout.length
          if(this.index < 12)
            {
              this.index = 12
            }


          }


         })



    },
    data() {

        return {
            layout: [
            ],
            seats : [],
            draggable: true,
            resizable: true,
            colNum : 0,
            index: 0,
            eventLog: [],
            modalData :{x:16, y :0,  w :4, h :4, i  :  "출입문" ,  used :false},
            modalVisible : false,
            dataidx : 0
        }
    },
    watch: {
        eventLog: function() {
            const eventsDiv = this.$refs.eventsDiv;
            eventsDiv.scrollTop = eventsDiv.scrollHeight;
        }
    },
    methods: {
       closeModal(){

          this.modalVisible = false;


        },
        seatChangeState(e){
          this.layout[e].used = false
          this.layout[e].id = null
          this.layout[e].frontState = 0
          this.seats[e].userId = null
          this.seats[e].frontState = 0

          console.log(this.layout[e])

        },
        seatShowModal(item,idx){

          if(item.i != "출입문")
          {

             this.modalData = item
             this.dataidx=idx
            this.modalVisible = true;
          }


        },
         seatUpdateAll(){

          this.layout.forEach((element,index) =>{

            if(element.i !="출입문")
            {

               this.seats[index].feCoordinateX=parseInt(element.x/2)
               this.seats[index].feCoordinateY=parseInt(element.y/2)
               if(element.used)
               {
                 this.seats[index].frontState = 1
               }
               else
               {
                 this.seats[index].frontState = 0
               }


            }


          })


          this.seats.forEach(element => {


          var obj = {
                      feCoordinateX : element.feCoordinateX ,
                      feCoordinateY : element.feCoordinateY,
                      frontState : element.frontState,
                      id : element.id,
                      userId : element.userId,
                      sectionId : this.$store.getters['library/getLibraryId']

          }
            this.$axios.put(this.$baseUrl + 'seat/fe',obj)

          })


         },
         seatRenew() {

         this.layout = []
         this.$axios.get(this.$baseUrl+'seat/list/'+this.$store.getters['library/getLibraryId'])
         .then(res => {

           this.seats = res.data
           var minX=9999999;
           var minY=9999999;
           var maxX=0;
           var maxY=0;
           this.seats.forEach(element =>{

             if(minX >element.coordinateX)
             {
               minX = element.coordinateX
             }
             else if(maxX < element.coordinateX)
             {
               maxX = element.coordinateX
             }

             if(minY > element.coordinateY)
             {
               minY = element.coordinateY
             }
             else if(maxY<element.coordinateY)
             {
               maxY = element.coordinateY
             }




           })
          var feMaxX = 0;
          this.seats.forEach(element =>{
            element.feCoordinateX = parseInt(element.coordinateX/minX);
            element.feCoordinateY = parseInt(element.coordinateY/minY);
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

          // modalData :{x:16, y :0,  w :4, h :4, i  :  "출입문" ,  used :false},
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
                seatNum : element.id,
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
                seatNum : element.id,
                frontState : element.frontState,


              }



            )



            }





          })
          this.index = this.layout.length
            if(this.index < 12)
            {
              this.index = 12
            }





         })



        },
        // seatRemoveItem() {
        //     if(this.index >1)
        //     {

        //       this.layout.splice(-1, 1);
        //       this.index--
        //     }


        // },
        moveEvent: function(i, newX, newY){
            const msg = "MOVE i=" + i + ", X=" + newX + ", Y=" + newY;
            this.eventLog.push(msg);
            console.log(msg);
        },
        movedEvent: function(i, newX, newY){
            const msg = "MOVED i=" + i + ", X=" + newX + ", Y=" + newY;
            this.eventLog.push(msg);
            console.log(msg);
        },
        resizeEvent: function(i, newH, newW, newHPx, newWPx){
            const msg = "RESIZE i=" + i + ", H=" + newH + ", W=" + newW + ", H(px)=" + newHPx + ", W(px)=" + newWPx;
            this.eventLog.push(msg);
            console.log(msg);
        },
        resizedEvent: function(i, newX, newY, newHPx, newWPx){
            const msg = "RESIZED i=" + i + ", X=" + newX + ", Y=" + newY + ", H(px)=" + newHPx + ", W(px)=" + newWPx;
            this.eventLog.push(msg);
            console.log(msg);
        },
        containerResizedEvent: function(i, newH, newW, newHPx, newWPx){
            const msg = "CONTAINER RESIZED i=" + i + ", H=" + newH + ", W=" + newW + ", H(px)=" + newHPx + ", W(px)=" + newWPx;
            this.eventLog.push(msg);
            console.log(msg);
        },
        layoutCreatedEvent: function(newLayout){
            this.eventLog.push("Created layout");
            console.log("Created layout: ", newLayout)
        },
        layoutBeforeMountEvent: function(newLayout){
            this.eventLog.push("beforeMount layout");
            console.log("beforeMount layout: ", newLayout)
        },
        layoutMountedEvent: function(newLayout){
            this.eventLog.push("Mounted layout");
            console.log("Mounted layout: ", newLayout)
        },
        layoutReadyEvent: function(newLayout){
            this.eventLog.push("Ready layout");
            console.log("Ready layout: ", newLayout)
        },
        layoutUpdatedEvent: function(newLayout){
            this.eventLog.push("Updated layout");
            console.log("Updated layout: ", newLayout)
        },
        seatMyClass : function(idx){
          if(this.layout[idx].frontState == 1)
          {
            return "seatOccupied"
          }
          else if(this.layout[idx].frontState == 2)
          {

            return "monopoly"
          }
          else{

            return "seat"
          }


      }


    }

}
</script>

<style scoped>
.vue-grid-layout {
    background: #eee;
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
.remove {
    position: absolute;
    right: 2px;
    top: 0;
    cursor: pointer;
}
.seat{
   background:skyblue;
    border: 1px solid black;

}

.seatOccupied{
   background:rgb(235, 135, 135);
    border: 1px solid black;

}

.seatSave-Btn{

  left : 92%;


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

.box-monopoly{

   width : 20px;
  height: 20px;
  background-color  : #fcb603;
  border: 1px solid black;
  float:left
}

.monopoly{

    background:#fcb603;
   border: 1px solid black;
}

</style>
