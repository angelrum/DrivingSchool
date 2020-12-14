<template>
  <v-stepper
    v-model="e6"
    vertical
  >
    <v-stepper-step
      :complete="e6 > 1"
      step="1"
    >
      Шаг 1. Создаем Администратора
      <small class="mt-1">Результат: создан профиль с ролью admin</small>
    </v-stepper-step>

    <v-stepper-content step="1">
      <v-card
        color="grey lighten-1"
        class="mb-12"
        height="200px"
      ></v-card>
      <v-btn
        color="primary"
        @click="e6 = 2"
      >
        Далее
      </v-btn>
      <v-btn text>
        Отмена
      </v-btn>
    </v-stepper-content>

    <v-stepper-step
      :complete="e6 > 2"
      step="2"
    >
      Шаг 2. Создаем Компанию
      <small class="mt-1">Результат: компания создана, администратор автоматически к ней привязан</small>
    </v-stepper-step>

    <v-stepper-content step="2">
      <v-card
        color="grey lighten-1"
        class="mb-12"
        height="200px"
      ></v-card>
      <v-btn
        color="primary"
        @click="e6 = 3"
      >
        Далее
      </v-btn>
      <v-btn text>
        Вернуться
      </v-btn>
    </v-stepper-content>

    <v-stepper-step
      :complete="e6 > 3"
      step="3"
    >
      Шаг 3. Изменение данных администратора
      <small class="mt-1">Результат: профиль обновлен</small>
    </v-stepper-step>

    <v-stepper-content step="3">
      <v-card
        color="grey lighten-1"
        class="mb-12"
        height="200px"
      ></v-card>
      <v-btn
        color="primary"
        @click="e6 = 4"
      >
        Далее
      </v-btn>
      <v-btn text>
        Вернуться
      </v-btn>
    </v-stepper-content>

    <v-stepper-step step="4">
      Шаг 4. Изменение данных школы
      <small class="mt-1">Результат: компания обновлена</small>
    </v-stepper-step>
    <v-stepper-content step="4">
      <v-card
        color="grey lighten-1"
        class="mb-12"
        height="200px"
      ></v-card>
      <v-btn
        color="primary"
        @click="e6 = 5"
      >
        Далее
      </v-btn>
      <v-btn text>
        Вернуться
      </v-btn>
    </v-stepper-content>

    <v-stepper-step step="5">
      Шаг 5. Вывод данных Администратора
      <small class="mt-1">Результат: отображение администратора с привязкой к компании</small>
    </v-stepper-step>
    <v-stepper-content step="5">
      <v-card
        color="grey lighten-1"
        class="mb-12"
        height="200px"
      ></v-card>
      <v-btn
        color="primary"
        @click="e6 = 1"
      >
        Завершить
      </v-btn>
    </v-stepper-content>
  </v-stepper>
</template>

<script>
export default {
    name: 'Betta',
    data () {
      return {
        e6: 1,
      }
    },
}
</script>