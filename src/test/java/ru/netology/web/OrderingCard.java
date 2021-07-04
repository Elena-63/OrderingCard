package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class OrderingCard {

        @Test
        void shouldFillForm() {
            open("http://localhost:9999");
            $(".form");
            $("[data-test-id=name] input").setValue("Иванов Иван");
            $("[data-test-id=phone] input").setValue("+79999999999");
            $("[data-test-id=agreement]").click();
            $(".button").click();
            $(".Success_successBlock__2L3Cw").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
        }

        @Test
        void shouldShowMistakeFieldName() {
            open("http://localhost:9999");
            $(".form");
            $("[data-test-id=name] input").setValue("Ivanov Ivan");
            $("[data-test-id=phone] input").setValue("+79999999999");
            $("[data-test-id=agreement]").click();
            $(".button").click();
            $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
        }

        @Test
        void shouldShowMistakeNoAgree() {
            open("http://localhost:9999");
            SelenideElement form = $(".form");
            form.$("[data-test-id=name] input").setValue("Иванов Иван");
            form.$("[data-test-id=phone] input").setValue("+79999999999");
            form.$(".button").click();
            $("[data-test-id='agreement'].input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
        }

        @Test
        void shouldShowMistakeEmptyFieldName() {
            open("http://localhost:9999");
            $(".form");
            $("[data-test-id=phone] input").setValue("+79999999999");
            $("[data-test-id=agreement]").click();
            $(".button").click();
            $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
        }

        @Test
        void shouldShowMistakeFieldTel() {
            open("http://localhost:9999");
            $(".form");
            $("[data-test-id=name] input").setValue("Иванов Иван");
            $("[data-test-id=phone] input").setValue("+71234567");
            $("[data-test-id=agreement]").click();
            $(".button").click();
            $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
        }

        @Test
        void shouldShowMistakeEmptyFieldTel() {
            open("http://localhost:9999");
            $(".form");
            $("[data-test-id=name] input").setValue("Иванов Иван");
            $("[data-test-id=agreement]").click();
            $(".button").click();
            $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
        }
    }