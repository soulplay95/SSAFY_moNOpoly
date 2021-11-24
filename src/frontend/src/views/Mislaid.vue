<template>
  <div>
    <base-header class="pb-6 pb-8 pt-5 pt-md-8">
    </base-header>
    <b-container fluid class="mt--7">
      <b-row class="justify-content-center">
        <b-col lg="12">
          <card header-classes="bg-transparent" class="backgroundCard">
            <h3 slot="header" class="mb-0 mislaidBoardTitle">유실물 게시판</h3>
            <div v-if="bulletins.length" class="bulletinsContent">
              <b-card-group deck>
                <b-card
                  v-for="bulletin in bulletins"
                  :key="bulletin.new_id"
                  :title="bulletin.title"
                  v-b-modal.mislaid-detail
                  @click="selected = bulletin.new_id"
                  class="cardBox mb-4"
                >
                  <!-- <img :src="makeImgUrl(bulletin.image)" class="img" /> -->
                  <p class="card-text cardText">
                      {{ bulletin.content }}
                  </p>
                </b-card>
              </b-card-group>
              <div>
                <b-modal id="mislaid-detail" title="유실물 상세 보기" class="modal " :hide-footer="true" centered>
                  <h3 class="font15"> {{ bulletins[selected].title }}</h3>
                  <p  class="font12">
                    습득일:  {{bulletins[selected].foundDate.split(' ')[0]}}
                  </p>
                  <p class="my-4 modalImageBox ">
                    <img :src="makeImgUrl(bulletins[selected].image)" class="img" />
                  </p>
                  <p  class="font12">
                  {{ bulletins[selected].content }}
                  </p>
                  <div v-if="isAdmin" class="detailModalControlBtnContainer">
                    <b-button
                      variant="danger"
                      @click="onClickModify"
                      v-b-modal.modifyCard
                      >수정
                    </b-button>
                    <b-button
                      variant="danger"
                      v-b-modal.delete-confirmation
                      >삭제
                    </b-button>
                  </div>
                </b-modal>
                <b-modal id="modifyCard" size="xl" title="유실물 게시글 수정" centered hide-footer>
                  <div class="modifyModal">
                    <b-form @submit="onModifySubmit" @reset="onModifyCancel" >
                      <b-form-group
                        id="input-group-modify-title"
                        label="제목"
                        label-for="input-modify-title"
                      ><b-form-input
                        id="input-title"
                        v-model="putForm.title"
                        required
                      ></b-form-input>
                      </b-form-group>

                      <b-form-group
                        id="input-group-modify-found-date"
                        label="습득일"
                        label-for="input-modify-found-date"
                      ><b-form-datepicker
                        id="input-modify-found-date"
                        v-model="putForm.foundDate"
                        :max="maxDate"
                        locale="ko"
                        required
                      ></b-form-datepicker>
                      </b-form-group>

                      <b-form-group
                        id="input-group-modify-content"
                        label="설명"
                        label-for="input-modify-content"
                      ><b-form-textarea
                        id="input-modify-content"
                        v-model="putForm.content"
                        rows="5"
                        no-resize
                        required
                      ></b-form-textarea>
                      </b-form-group>
                      <div class="mislaidCreateModalBtnContainer">
                        <b-button type="submit" variant="primary" >등록</b-button>
                        <b-button type="reset" variant="danger" >취소</b-button>
                      </div>
                    </b-form>
                  </div>
                </b-modal>
                <b-modal id="delete-confirmation" title="삭제 확인" :hide-footer="true" centered size="sm" class="deleteConfirmationModal">
                  <p class="my-2">정말 삭제하시겠습니까?</p>
                  <div class="deleteConfirmationModalBtnContainer">
                    <b-button
                      variant="danger"
                      @click="onClickDelete"
                      >확인
                    </b-button>
                    <b-button
                      variant="danger"
                      @click="$bvModal.hide('delete-confirmation')"
                      >취소
                    </b-button>
                  </div>
                  <!-- <b-button v-b-modal.modal-multi-3 size="sm">Open Third Modal</b-button> -->
                </b-modal>
              </div>
            </div>
            <div slot="footer">
              <b-button v-if="isAdmin" v-b-modal.createCard variant="primary" block  type="submit" class="mislaidCreateBtn">
                Add
                <b-modal id="createCard" size="xl" title="유실물 게시글 업로드" centered hide-footer>
                  <div class="createModal">
                    <b-form @submit="onSubmit" @reset="onCancel" >
                      <b-form-group
                        id="input-group-title"
                        label="제목"
                        label-for="input-title"
                      ><b-form-input
                        id="input-title"
                        v-model="form.title"
                        placeholder="Enter Title"
                        required
                      ></b-form-input>
                      </b-form-group>

                      <b-form-group
                        id="input-group-found-date"
                        label="습득일"
                        label-for="input-found-date"
                      ><b-form-datepicker
                        id="input-found-date"
                        v-model="form.foundDate"
                        :max="maxDate"
                        locale="ko"
                        required
                      ></b-form-datepicker>
                      </b-form-group>

                      <p>
                              <input
                              type="file"
                              id="imageFile"
                              ref="imageFile"
                              class="imageFile"
                              accept="image/*"
                              required
                              multiple
                            />
                      </p>

                      <b-form-group
                        id="input-group-content"
                        label="설명"
                        label-for="input-content"
                      ><b-form-textarea
                        id="input-content"
                        v-model="form.content"
                        placeholder="Enter description"
                        rows="5"
                        no-resize
                        required
                      ></b-form-textarea>
                      </b-form-group>
                      <div class="mislaidCreateModalBtnContainer">
                        <b-button type="submit" variant="primary" >등록</b-button>
                        <b-button type="reset" variant="danger" >취소</b-button>
                      </div>
                    </b-form>
                  </div>
                </b-modal>
              </b-button>
            </div>
          </card>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import BaseHeader from "@/components/BaseHeader";

export default {
  name: "Mislaid",
  components: {
    BaseHeader,
  },
  data() {
    const today = new Date()
    const maxDate = new Date(today.getFullYear(), today.getMonth(), today.getDate())

    return {
      bulletins: [],
      newBulletin:{
        id: 0,
        penaltyLogId: 3,
        libraryId: this.libId,
        title: '',
        content: '',
        foundDate: '',
        new_id: 0,
        imgsrc : null,
      },
      selected: 0,
      form:
      {
        content: '',
        foundDate: '',
        libraryId: this.libId,
        penaltyLogId: null,
        title: '',

        image: '',
        file:[],
        files : [],
        imgsrc :""
      },
      putForm: {
        content: '',
        foundDate: '',
        expireDate: '',
        id: 0,
        title: '',

      },
      maxDate: maxDate,
      userId : 0,
      isAdmin: false,
      adminId : null,
      libId : null,
      selectedFile : null,
    };
  },
  mounted(){
    this.userId = this.$store.getters['user/getUserId']
    this.libId = this.$store.getters['library/getLibraryId']

    this.getAdminId()



  },
  methods: {
    makeImgUrl(e){


      var url ='data:image/png;base64,' +
                btoa(new Uint8Array(e).reduce(function (data, byte) {
                  return data + String.fromCharCode(byte);
              }, ''))


      return url
    },
    getAdminId(){
      this.$axios.get(this.$baseUrl+"admin/"+this.libId)
      .then(res => {
        if(res.data[0].id == this.userId)
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
    onFileSelected(event) {
      console.log(event.target.files[0])
      this.selectedFile = event.target.files[0]
      const fd = new FormData();
      fd.append('image', this.selectedFile, this.selectedFile.name)
      this.$axios.post('gs://monopoly-da740.appspot.com', fd)
      .then(res => {
        console.log(res);
      })
    },
    onSubmit(event) {
      event.preventDefault()
      this.form.foundDate = this.form.foundDate + " 00:00:00";
      this.form.libraryId = this.libId;

       this.form.files = this.$refs.imageFile.files; // 파일 리스트

      for (let i = 0; i < this.form.files.length; i++) {
        let curFile = this.form.files[i];
        let file = new FormData();
        file.append("file", curFile);

      this.$axios.post(this.$baseUrl +'locker?content='+this.form.content+'&foundDate='+this.form.foundDate+'&libraryId='+this.form.libraryId+'&penaltyLogId=1&title='+this.form.title,file)
      .then(res =>{

         window.location.reload();

      })
      }




    },
    onCancel(event) {
      event.preventDefault()
      // Reset our form values
      this.form.content = ''
      this.form.foundDate = ''
      this.form.title = ''
      this.$bvModal.hide('createCard')
    },
    onClickModify(event) {
      event.preventDefault()
      this.putForm.content = this.bulletins[this.selected].content;
      this.putForm.foundDate = this.bulletins[this.selected].foundDate;
      this.putForm.title = this.bulletins[this.selected].title;
      this.putForm.id = this.bulletins[this.selected].id;

    },
    onModifySubmit(event) {
      event.preventDefault()
      if(this.putForm.foundDate.length == 10){
        this.putForm.foundDate = this.putForm.foundDate + " 00:00:00";
      }

      this.$axios.put(this.$baseUrl +'locker', this.putForm)
      .then(res => {
        // 페이지 새로고침
        window.location.reload();
      })
    },
    onModifyCancel(event) {
      event.preventDefault()
      // Reset our form values
      this.putForm.content = ''
      this.putForm.foundDate = ''
      this.putForm.title = ''
      this.$bvModal.hide('modifyCard')
    },
    onClickDelete(event) {
      event.preventDefault()
      this.$axios.delete(this.$baseUrl +'locker/' + this.bulletins[this.selected].id)
      .then(res => {
        // 페이지 새로고침
        window.location.reload();
      })
    },
    getTimeStamp(date)
    {
      var YYYY = date.getFullYear();
      var MM = date.getMonth() + 1;
      var DD = date.getDate();

      YYYY = String(YYYY);
      MM = MM < 10 ? '0' + String(MM) : String(MM);
      DD = DD < 10 ? '0' + String(DD) : String(DD);

      var hh = date.getHours();
      var mm = date.getMinutes();
      var ss = date.getSeconds();

      hh = hh < 10 ? '0' + String(hh) : String(hh);
      mm = mm < 10 ? '0' + String(mm) : String(mm);
      ss = ss < 10 ? '0' + String(ss) : String(ss);

      return YYYY + '-' + MM + '-' + DD + 'T' + hh + ':' + mm + ':' + ss + '.000';
    }
  },
  created() {
    this.$axios.get(this.$baseUrl + 'locker/list/' + this.$store.getters['library/getLibraryId'])
    .then(res => {
      // res.data.forEach(element =>{

      //   var obj = {

      //     content : element.content,
      //     createdDate : element.createdDate,
      //     expireDate : element.expireDate,
      //     foundDate : element.foundDate,
      //     id : element.id,
      //     imgsrc : "data:image/png;base64," + btoa(
      //             String.fromCharCode.apply(null, new Uint8Array(element.image))
      //           ),
      //     libraryId : element.libraryId,
      //     modifiedDate : element.modifiedDate,
      //     penaltyLogId : element.penaltyLogId,
      //     title : element.title


      //   }
      //   element.imgsrc = "data:image/png;base64," +
      //           btoa(
      //             String.fromCharCode.apply(null, new Uint8Array(element.image))
      //           );
      //   var imgsrc = "data:image/png;base64," +
      //           btoa(
      //             String.fromCharCode.apply(null, new Uint8Array(element.image))
      //           )
      //   this.bulletins.push(element)

      // })


      res.data.reduce((acc, cur, idx ) => {
        cur.new_id = res.data.length - 1 - idx;
        acc.unshift(cur);
        return acc;
      }, this.bulletins)
    })
    .catch(err => {
      console.log("ERROR", err)
    })
  },

};
</script>

<style>
.backgroundCard {
  height: 75vh;
}

.bulletinsContent {
  height: 50vh;
  overflow-x: hidden;
  overflow-y: auto;
}

.mislaidBoardTitle {
  font-size: 1.5em;
}

.b-card-group .card {
  /* max-width: 25%; */
}

.card-deck .card {
  max-width: calc(5% - 30px);
  width: 50px;
}
.inputfile {
  display: none;
}
.input-plus {
  cursor: pointer;
}

div.card.cardBox {
  min-width: 200px;
  height: 250px;
  display: block;
  overflow: hidden;
  text-overflow: ellipsis;
}

.modalImageBox {
  height: 300px;
}

.modalImageBox .modalImage {
  position: absolute;
  top: 20px;
  left: 10%;
  width: 80%;
  max-height: 50%;
}

.b-form-btn-label-control {
  padding: 0 0;
  display: flex;
  height: 46px;
}

#input-found-date__value_, #input-modify-found-date__value_ {
  border: 0px;
}

.dropdown-menu, .b-calendar-header, .b-calendar-nav, .b-calendar-grid {
  width: 450px;
}

.mislaidCreateBtn {
  max-width: 100px;
  margin: auto;

}

.mislaidCreateModalBtnContainer, .detailModalControlBtnContainer, .deleteConfirmationModalBtnContainer {
  max-width: 160px;
  margin: auto;
}

.deleteConfirmationModal {
  background-color: bisque;
}

.font15 {
  font-size: 1.5em;
}

.font12 {
  font-size: 1.2em;
}

.img{

  width : 100%;
  height: 100%;
}

</style>
