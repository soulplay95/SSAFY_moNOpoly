import { Mark } from "tiptap";
import { updateMark } from "tiptap-commands";

export default class CustomStyle extends Mark {
  get name() {
    return "customstyle";
  }

  get defaultOptions() {
    return {
      levels: ["body-green", "body-red", "body-blue"]
    };
  }

  get schema() {
    return {
      attrs: {
        level: {
          default: "body-green"
        }
      },
      parseDOM: [
        {
          tag: "span.custom-style",
          getAttrs(dom) {
            console.log(dom.classList[1]);
            return { level: dom.classList[1] };
          }
        }
      ],
      toDOM: mark => {
        return ["span", { class: `custom-style ${mark.attrs.level}` }, 0];
      }
    };
  }

  commands({ type }) {
    return attrs => updateMark(type, attrs);
  }
}
