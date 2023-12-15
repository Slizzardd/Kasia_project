package ua.com.alevel.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Block {
    private String type;
    private BlockData data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class BlockData {
        private String text;
        private int level;
        private List<String> items;
        private String style;
        private String source;
        private String caption;
        private boolean stretched;
        private boolean withBorder;
        private boolean withBackground;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public List<String> getItems() {
            return items;
        }

        public void setItems(List<String> items) {
            this.items = items;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getCaption() {
            return caption;
        }

        public void setCaption(String caption) {
            this.caption = caption;
        }

        public boolean isStretched() {
            return stretched;
        }

        public void setStretched(boolean stretched) {
            this.stretched = stretched;
        }

        public boolean isWithBorder() {
            return withBorder;
        }

        public void setWithBorder(boolean withBorder) {
            this.withBorder = withBorder;
        }

        public boolean isWithBackground() {
            return withBackground;
        }

        public void setWithBackground(boolean withBackground) {
            this.withBackground = withBackground;
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BlockData getData() {
        return data;
    }

    public void setData(BlockData data) {
        this.data = data;
    }
}
