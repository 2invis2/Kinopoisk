package com.invis.kinopoisk.exception;

public class ExceptionConst extends IllegalStateException {

        public static final String REFRESH_NULL = "С такими жанрами фильмов нет";

 public ExceptionConst(String masage) {
  super(masage);
 }
}
