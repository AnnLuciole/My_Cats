package sql.demo.dataForTables;

public class DataForCats {

    private static String[] names = {"Гарфилд",
            "Том",
            "Гудвин",
            "Рокки",
            "Ленивец",
            "Пушок",
            "Спорти",
            "Бегемот",
            "Пират",
            "Гудини",
            "Зорро",
            "Саймон",
            "Альбус",
            "Базилио",
            "Леопольд",
            "Нарцисс",
            "Атос",
            "Каспер",
            "Валлито",
            "Оксфорд",
            "Бисквит",
            "Соня",
            "Клеопатра",
            "Цунами",
            "Забияка",
            "Матильда",
            "Кнопка",
            "Масяня",
            "Царапка",
            "Серсея",
            "Ворсинка",
            "Амели",
            "Наоми",
            "Маркиза",
            "Изольда",
            "Вальс",
            "Несквик",
            "Златан",
            "Баскет",
            "Изюм",
            "Цукат",
            "Мокко",
            "Месси",
            "Кокос",
            "Адидас",
            "Бейлиз",
            "Тайгер",
            "Зефир",
            "Мохи",
            "Валенсия",
            "Баунти",
            "Свити",
            "Текила",
            "Ириска",
            "Карамель",
            "Виски",
            "Кукуруза",
            "Гренка",
            "Фасолька",
            "Льдинка",
            "Китана",
            "Офелия",
            "Дайкири",
            "Брусника",
            "Аватар",
            "Космос",
            "Призрак",
            "Изумруд",
            "Плинтус",
            "Яндекс",
            "Крисмас",
            "Метеор",
            "Оптимус",
            "Смайлик",
            "Цельсий",
            "Краска",
            "Дейзи",
            "Пенка",
            "Веста",
            "Астра",
            "Эйприл",
            "Среда",
            "Бусинка",
            "Гайка",
            "Елка",
            "Золушка",
            "Мята",
            "Радость",
            "Сиам",
            "Оскар",
            "Феликс",
            "Гарри",
            "Байрон",
            "Чарли",
            "Симба",
            "Тао",
            "Абу",
            "Ватсон",
            "Енисей",
            "Измир",
            "Кайзер",
            "Васаби",
            "Байкал",
            "Багира",
            "Айрис",
            "Диана",
            "Мими",
            "Сакура",
            "Индия",
            "Тиффани",
            "Скарлетт",
            "Пикси",
            "Лиззи",
            "Алиса",
            "Лило",
            "Ямайка",
            "Пэрис",
            "Мальта",
            "Аляска"
    };

    public static String getRandom() {
        String randomName = names[(int) (Math.random() * names.length)];
        return randomName;
    }
}
