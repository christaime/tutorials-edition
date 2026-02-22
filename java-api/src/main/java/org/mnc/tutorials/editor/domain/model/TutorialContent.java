package org.mnc.tutorials.editor.domain.model;

import java.util.List;

public class TutorialContent{
    private List<ContentBlock> blocks;

    public void addBlock(ContentBlock block) {
        this.blocks.add(block);
    }

    // Logic for versioning or AI analysis goes here

    // Sealed interface for polymorphic content (Java 17+)
    public sealed interface ContentBlock permits MarkdownBlock, MediaBlock, LinkBlock {}

    public record MarkdownBlock(String markdownText) implements ContentBlock {}
    public record MediaBlock(String url, String type) implements ContentBlock {} // Image/Video
    public record LinkBlock(String link, String uri) implements ContentBlock {}
}

