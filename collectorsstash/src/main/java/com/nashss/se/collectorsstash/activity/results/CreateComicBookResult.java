package com.nashss.se.collectorsstash.activity.results;

import com.nashss.se.collectorsstash.models.ComicBookModel;

public class CreateComicBookResult {
    private final ComicBookModel comicBook;
    /**
     * Constructs a new result object for the creation of a comic book.
     *
     * @param comicBook The comic book model representing the newly created comic book.
     */
    public CreateComicBookResult(ComicBookModel comicBook) {
        this.comicBook = comicBook;
    }

    public ComicBookModel getComicBook() {
        return comicBook;
    }

    @Override
    public String toString() {
        return "CreateComicBookRequest{" +
                "comicBook=" + comicBook +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
            private ComicBookModel comicBook;

            public Builder withComicBook(ComicBookModel comicBook) {
                this.comicBook = comicBook;
                return this;
            }
            public CreateComicBookResult build() {
                return new CreateComicBookResult(comicBook);
            }
        }
}
