# Calorie Calculator 🍎

Современное Android-приложение для подсчета дневного потребления калорий с использованием **Java**, **Room Database**, **RecyclerView** и **View Binding**.

## ✨ Возможности

* Расчет дневной нормы калорий
* Добавление продуктов и калорий
* Подсчет общего количества калорий
* Отображение оставшегося лимита
* Удаление продуктов из списка
* Локальное хранение данных через Room Database
* Современный интерфейс Material Design

---


## 🧮 Формула расчета калорий

Используется формула Харриса-Бенедикта:

```text
88.362 + (13.397 × вес) + (4.799 × рост) − (5.677 × возраст)
```

---

## 🛠 Технологии

* Java
* Android SDK 26+
* View Binding
* RecyclerView
* Room Database
* Material Components
* SharedPreferences

---

## 📂 Структура проекта

```text
com.w1ps.caloriecalculator
│
├── data
│   ├── AppDatabase.java
│   ├── FoodDao.java
│   └── FoodItem.java
│
├── ui
│   ├── main
│   │   ├── MainActivity.java
│   │   └── FoodAdapter.java
│   │
│   └── start
│       └── StartActivity.java
│
├── res
│   └── layout
│       ├── activity_main.xml
│       ├── activity_start.xml
│       └── item_food.xml
│
└── AndroidManifest.xml
```

---

## 🚀 Запуск проекта

1. Клонировать репозиторий

```bash
git clone <repo-url>
```

2. Открыть проект в Android Studio

3. Выполнить:

```text
Sync Project with Gradle Files
```

4. Запустить приложение

---

## 📦 Зависимости

```gradle
    implementation ("androidx.appcompat:appcompat:1.7.0")
    implementation ("com.google.android.material:material:1.12.0")
    implementation ("androidx.recyclerview:recyclerview:1.3.2")

    // ROOM
    implementation ("androidx.room:room-runtime:2.6.1")
    annotationProcessor ("androidx.room:room-compiler:2.6.1")
```

---

## 🎯 Особенности проекта

* Используется современный подход View Binding
* Room Database вместо обычного SQLite
* Простая масштабируемость
* Подходит как учебный проект Android

