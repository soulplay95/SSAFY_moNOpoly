<template>
  <div>
    <div v-if="editor">
      <button
        @click="
          editor
            .chain()
            .focus()
            .toggleBold()
            .run()
        "
        :class="{ 'is-active': editor.isActive('bold') }"
        title="Bold"
      >
        <b-icon
          icon="type-bold"
          aria-hidden="true"
          style="width: 25px; height: 25px;"
        ></b-icon>
      </button>
      <dfn data-info="Italic">
        <button
          @click="
            editor
              .chain()
              .focus()
              .toggleItalic()
              .run()
          "
          :class="{ 'is-active': editor.isActive('italic') }"
          title="Italic"
        >
          <b-icon
            icon="type-italic"
            aria-hidden="true"
            style="width: 25px; height: 25px;"
          ></b-icon>
        </button>
      </dfn>
      <button
        @click="
          editor
            .chain()
            .focus()
            .toggleUnderline()
            .run()
        "
        :class="{ 'is-active': editor.isActive('underline') }"
        title="Underline"
      >
        <b-icon
          icon="type-underline"
          aria-hidden="true"
          style="width: 25px; height: 25px;"
        ></b-icon>
      </button>
      <button
        @click="
          editor
            .chain()
            .focus()
            .toggleStrike()
            .run()
        "
        :class="{ 'is-active': editor.isActive('strike') }"
        title="Strike"
      >
        <b-icon
          icon="type-strikethrough"
          aria-hidden="true"
          style="width: 25px; height: 25px;"
        ></b-icon>
      </button>
      <button
        @click="
          editor
            .chain()
            .focus()
            .toggleCode()
            .run()
        "
        :class="{ 'is-active': editor.isActive('code') }"
        title="Highlight"
      >
        <img
          src="..\..\assets\editorIcon\highlight.png"
          width="25px"
          height="25px"
        />
      </button>
      <button
        @click="
          editor
            .chain()
            .focus()
            .toggleHeading({ level: 1 })
            .run()
        "
        :class="{ 'is-active': editor.isActive('heading', { level: 1 }) }"
        title="H1"
      >
        <b-icon
          icon="type-h1"
          aria-hidden="true"
          style="width: 25px; height: 25px;"
        ></b-icon>
      </button>
      <button
        @click="
          editor
            .chain()
            .focus()
            .toggleHeading({ level: 2 })
            .run()
        "
        :class="{ 'is-active': editor.isActive('heading', { level: 2 }) }"
        title="H2"
      >
        <b-icon
          icon="type-h2"
          aria-hidden="true"
          style="width: 25px; height: 25px;"
        ></b-icon>
      </button>
      <button
        @click="
          editor
            .chain()
            .focus()
            .toggleHeading({ level: 3 })
            .run()
        "
        :class="{ 'is-active': editor.isActive('heading', { level: 3 }) }"
        title="H3"
      >
        <b-icon
          icon="type-h3"
          aria-hidden="true"
          style="width: 25px; height: 25px;"
        ></b-icon>
      </button>
      <button
        @click="
          editor
            .chain()
            .focus()
            .toggleBulletList()
            .run()
        "
        :class="{ 'is-active': editor.isActive('bulletList') }"
        title="Bullet List"
      >
        <b-icon
          icon="list-ul"
          aria-hidden="true"
          style="width: 25px; height: 25px;"
        ></b-icon>
      </button>
      <button
        @click="
          editor
            .chain()
            .focus()
            .toggleOrderedList()
            .run()
        "
        :class="{ 'is-active': editor.isActive('orderedList') }"
        title="Ordered List"
      >
        <b-icon
          icon="list-ol"
          aria-hidden="true"
          style="width: 25px; height: 25px;"
        ></b-icon>
      </button>
      <!-- <button @click="editor.chain().focus().toggleCodeBlock().run()" :class="{ 'is-active': editor.isActive('codeBlock') }">
      code block
      <b-icon icon="code" aria-hidden="true" style="width: 25px; height: 25px;"></b-icon>
    </button> -->
      <button @click="addImage" class="mb-1" title="Add Image">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          style="width: 25px; height: 25px"
          fill="none"
          viewBox="0 0 24 24"
          stroke="currentColor"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            stroke-width="2"
            d="M4 16l4.586-4.586a2 2 0 012.828 0L16 16m-2-2l1.586-1.586a2 2 0 012.828 0L20 14m-6-6h.01M6 20h12a2 2 0 002-2V6a2 2 0 00-2-2H6a2 2 0 00-2 2v12a2 2 0 002 2z"
          />
        </svg>
      </button>
      <button
        @click="
          editor
            .chain()
            .focus()
            .undo()
            .run()
        "
        style="width: 41px; height: 31.81px;"
        title="Undo"
      >
        <img
          src="..\..\assets\editorIcon\undo.png"
          width="25px"
          height="25px"
          class="px-1 py-1"
        />
      </button>
      <button
        @click="
          editor
            .chain()
            .focus()
            .redo()
            .run()
        "
        style="width: 41px; height: 31.81px;"
        title="Redo"
      >
        <img
          src="..\..\assets\editorIcon\redo.png"
          width="25px"
          height="25px"
          class="px-1 py-1"
        />
      </button>

      <br />
      <!--
    <button @click="editor.chain().focus().insertTable({ rows: 3, cols: 3, withHeaderRow: true }).run()" title="Insert Table">
      <b-icon icon="table" aria-hidden="true" style="width: 25px; height: 25px;"></b-icon>
    </button>
    <button @click="editor.chain().focus().addRowBefore().run()" :disabled="!editor.can().addRowBefore()" title="Add Row Before">
      <img src="..\..\assets\editorIcon\addRowBefore.png" width="25px" height="25px">
    </button>
    <button @click="editor.chain().focus().addRowAfter().run()" :disabled="!editor.can().addRowAfter()" title="Add Row After">
      <img src="..\..\assets\editorIcon\addRowAfter.png" width="25px" height="25px">
    </button>
    <button @click="editor.chain().focus().deleteRow().run()" :disabled="!editor.can().deleteRow()" title="Delete Row">
      <img src="..\..\assets\editorIcon\deleteRow.png" width="25px" height="25px">
    </button>
    <button @click="editor.chain().focus().addColumnBefore().run()" :disabled="!editor.can().addColumnBefore()" title="Add Column Before">
      <img src="..\..\assets\editorIcon\addColumnBefore.png" width="25px" height="25px">
    </button>
    <button @click="editor.chain().focus().addColumnAfter().run()" :disabled="!editor.can().addColumnAfter()" title="Add Column After">
      <img src="..\..\assets\editorIcon\addColumnAfter.png" width="25px" height="25px">
    </button>
    <button @click="editor.chain().focus().deleteColumn().run()" :disabled="!editor.can().deleteColumn()" title="Delete Column">
      <img src="..\..\assets\editorIcon\deleteColumn.png" width="25px" height="25px">
    </button>
    <button @click="editor.chain().focus().deleteTable().run()" :disabled="!editor.can().deleteTable()" title="Delete Table">
      <img src="..\..\assets\editorIcon\deleteTable.png" width="25px" height="25px">
    </button>
    <button @click="editor.chain().focus().mergeCells().run()" :disabled="!editor.can().mergeCells()" title="Merge Cells">
      <img src="..\..\assets\editorIcon\mergeCells.png" width="25px" height="25px">
    </button>
    <button @click="editor.chain().focus().splitCell().run()" :disabled="!editor.can().splitCell()" title="Split Cell">
      <img src="..\..\assets\editorIcon\splitCells.png" width="25px" height="25px">
    </button>
    <button @click="editor.chain().focus().toggleHeaderRow().run()" :disabled="!editor.can().toggleHeaderRow()" title="Toggle Header Row">
      <img src="..\..\assets\editorIcon\toggleRow.png" width="25px" height="25px">
    </button>
    <button @click="editor.chain().focus().toggleHeaderColumn().run()" :disabled="!editor.can().toggleHeaderColumn()" title="Toggle Header Column">
      <img src="..\..\assets\editorIcon\toggleColumn.png" width="25px" height="25px">
    </button> -->
      <br />
    </div>
    <!-- <hr> -->
    <editor-content :editor="editor" />
    <b-modal
      centered
      ref="my-modal"
      hide-footer
      title="이미지업로드"
      @hidden="hideModal"
    >
      <input
        type="file"
        id="imageFile"
        ref="imageFile"
        class="imageFile"
        accept="image/*"
        v-on:change="uploadImage"
        required
        multiple
      />
      <b-button class="mt-2" variant="outline-warning" block @click="submitImg"
        >업로드</b-button
      >
      <b-button class="mt-2" variant="outline-warning" block @click="hideModal"
        >확인</b-button
      >
    </b-modal>

    <!-- <div class="export">
    <hr>
    <h3>HTML</h3>
    <pre><code>{{ html }}</code></pre>
  </div> -->
  </div>
</template>

<script>
import { Editor, EditorContent } from "@tiptap/vue-2";
import StarterKit from "@tiptap/starter-kit";
import Table from "@tiptap/extension-table";
import TableRow from "@tiptap/extension-table-row";
import TableCell from "@tiptap/extension-table-cell";
import TableHeader from "@tiptap/extension-table-header";
import Image from "@tiptap/extension-image";
import Underline from "@tiptap/extension-underline";
import axios from "axios";
import firebase from "firebase/app";
import "firebase/storage";

const CustomTableCell = TableCell.extend({
  // addAttributes() {
  //   return {
  //     // extend the existing attributes …
  //     ...this.parent?.(),
  //     // and add a new one …
  //     backgroundColor: {
  //       default: null,
  //       parseHTML: element => {
  //         return {
  //           backgroundColor: element.getAttribute('data-background-color'),
  //         }
  //       },
  //       renderHTML: attributes => {
  //         return {
  //           'data-background-color': attributes.backgroundColor,
  //           style: `background-color: ${attributes.backgroundColor}`,
  //         }
  //       },
  //     },
  //   }
  // }
});

export default {
  methods: {
    submitImg() {
      // 여러 파일 처리
      let files = this.file;

      for (let i = 0; i < files.length; i++) {
        let curFile = files[i];
        // 파일 크기 5MB 미만
        if (curFile.size < 5e6) {
          let formData = new FormData();

          formData.append("image", curFile);

          // for (var key of formData.keys()) {
          //   console.log(key);
          // }

          // for (var value of formData.values()) {
          //   console.log(value);
          // }

          // upload using imgur api
          fetch("https://api.imgur.com/3/image", {
            method: "POST",
            headers: {
              Authorization: "ad5fcda8428d334",
              Accept: "application/json"
            },
            body: formData
          })
            .then(response => response.json())
            .then(response => {
              console.log(response);
              // do Something
            });
        } else {
          console.error("파일 용량 초과");
        }
      }
      // let formData = new FormData();

      // formData.append("file", this.file);
      // console.log(formData);
    },
    // 파일 업로드
    uploadImage() {
      // init
      this.files = this.$refs.imageFile.files; // File list
      firebase.initializeApp(firebaseConfig);
      var storageRef = firebase.storage().ref(); // firebase storage ref

      // handle multiple files
      for (let i = 0; i < this.files.length; i++) {
        let curFile = this.files[i]; // 현재 파일 객체

        console.log(curFile.name);
        // 각 파일별 ref를 만들고 put()을 이용해 업로드
        let storage = storageRef.child("notice/" + curFile.name);
        storage.put(curFile).then(function(snapshot) {
          console.log("Success upload file: " + curFile.name);
        });
      }
    },
    showModal() {
      this.$refs["my-modal"].show();
    },
    hideModal() {
      this.$refs["my-modal"].hide();
      this.$emit("closeNotice");
    },
    addImage() {
      this.showModal();
    }
  },
  created() {
    this.notices = JSON.parse(
      JSON.stringify(this.$store.getters["notice/getNotice"])
    );
  },
  mounted() {
    this.$refs["my-modal"].hide();
    this.editor = new Editor({
      extensions: [
        StarterKit,
        Table.configure({
          resizable: true
        }),
        TableRow,
        TableHeader,
        // Default TableCell
        // TableCell,
        // Custom TableCell with backgroundColor attribute
        CustomTableCell,
        Image,
        // Dropcursor,
        Underline
      ],
      content: "",
      autofocus: true
    });
    this.editor.commands.setContent(this.preHtml);
    this.html = this.editor.getHTML();

    this.editor.on("update", () => {
      this.html = this.editor.getHTML();
      // console.log(this.html);
      this.$emit("update-context", this.html);
    });
  },
  components: {
    EditorContent
  },
  props: {
    preHtml: {
      type: String,
      // required: true,
      default: ""
    }
  },
  watch: {
    preHtml(val) {
      this.preHtml = val;
      this.editor.commands.setContent(val);
    }
  },
  data() {
    return {
      notice_id: 0,
      notices: [],
      editor: null,
      html: "",
      files: []
    };
  },
  beforeDestroy() {
    this.editor.destroy();
  }
};
</script>

<style lang="scss">
/* Basic editor styles */
.ProseMirror {
  > * + * {
    margin-top: 0.75em;
  }

  img {
    max-width: 100%;
    height: auto;
    &.ProseMirror-selectednode {
      outline: 3px solid #68cef8;
    }
  }

  ul,
  ol {
    padding: 0 1rem;
  }
  h1,
  h2,
  h3,
  h4,
  h5,
  h6 {
    line-height: 1.1;
  }
  code {
    background-color: rgba(#616161, 0.1);
    color: #616161;
  }
  pre {
    background: #0d0d0d;
    color: #fff;
    font-family: "JetBrainsMono", monospace;
    padding: 0.75rem 1rem;
    border-radius: 0.5rem;
    code {
      color: inherit;
      padding: 0;
      background: none;
      font-size: 0.8rem;
    }
  }
  img {
    max-width: 100%;
    height: auto;
  }
  blockquote {
    padding-left: 1rem;
    border-left: 2px solid rgba(#0d0d0d, 0.1);
  }
  hr {
    border: none;
    border-top: 2px solid rgba(#0d0d0d, 0.1);
    // margin: 2rem 0;
  }
}

/* Table-specific styling */
.ProseMirror {
  table {
    border-collapse: collapse;
    table-layout: fixed;
    width: 100%;
    margin: 0;
    overflow: hidden;
    td,
    th {
      min-width: 1em;
      border: 2px solid #ced4da;
      padding: 3px 5px;
      vertical-align: top;
      box-sizing: border-box;
      position: relative;
      > * {
        margin-bottom: 0;
      }
    }
    th {
      font-weight: bold;
      text-align: left;
      background-color: #f1f3f5;
    }
    .selectedCell:after {
      z-index: 2;
      position: absolute;
      content: "";
      left: 0;
      right: 0;
      top: 0;
      bottom: 0;
      background: rgba(200, 200, 255, 0.4);
      pointer-events: none;
    }
    .column-resize-handle {
      position: absolute;
      right: -2px;
      top: 0;
      bottom: -2px;
      width: 4px;
      background-color: #adf;
      pointer-events: none;
    }
  }
}
.tableWrapper {
  overflow-x: auto;
}
.resize-cursor {
  cursor: ew-resize;
  cursor: col-resize;
}
</style>

<style>
div.ProseMirror {
  min-height: 250px;
  border: 1px solid black;
}
</style>
