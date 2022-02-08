@file:Suppress("UNUSED_PARAMETER")

package lesson12.task1

/**
 * Класс "Телефонная книга".
 *
 * Общая сложность задания -- средняя, общая ценность в баллах -- 14.
 * Объект класса хранит список людей и номеров их телефонов,
 * при чём у каждого человека может быть более одного номера телефона.
 * Человек задаётся строкой вида "Фамилия Имя".
 * Телефон задаётся строкой из цифр, +, *, #, -.
 * Поддерживаемые методы: добавление / удаление человека,
 * добавление / удаление телефона для заданного человека,
 * поиск номера(ов) телефона по заданному имени человека,
 * поиск человека по заданному номеру телефона.
 *
 * Класс должен иметь конструктор по умолчанию (без параметров).
 */
class PhoneBook {
    private var start: Human? = null

    private class Human(
        val name: String,
        var phones: Set<String> = emptySet(),
        var next: Human?
    )

    val size: Int
        get() {
            var current = start
            var result = 0
            while (current != null) {
                current = current.next
                result++
            }
            return result
        }

    /**
     * Добавить человека.
     * Возвращает true, если человек был успешно добавлен,
     * и false, если человек с таким именем уже был в телефонной книге
     * (во втором случае телефонная книга не должна меняться).
     */
    fun addHuman(name: String): Boolean {
        if (start != null) {
            val start = start
            if (start?.name == name) return false
            var current = start
            while (current?.next != null) {
                if (current.next!!.name == name) return false
                current = current.next
            }
        }
        start = Human(name, next = start)
        return true
    }

    /**
     * Убрать человека.
     * Возвращает true, если человек был успешно удалён,
     * и false, если человек с таким именем отсутствовал в телефонной книге
     * (во втором случае телефонная книга не должна меняться).
     */
    fun removeHuman(name: String): Boolean {
        val start = start ?: return false
        if (start.name == name) {
            this.start = start.next
            return true
        }
        var current = start
        while (current.next != null) {
            if (current.next?.name == name) {
                current.next = current.next?.next
                return true
            }
            current = current.next!!
        }
        return false
    }

    /**
     * Добавить номер телефона.
     * Возвращает true, если номер был успешно добавлен,
     * и false, если человек с таким именем отсутствовал в телефонной книге,
     * либо у него уже был такой номер телефона,
     * либо такой номер телефона зарегистрирован за другим человеком.
     */
    fun addPhone(name: String, phone: String): Boolean {
        val start = start ?: return false
        if (start.phones.contains(phone)) return false
        var guy: Human? = null
        if (start.name == name) guy = start
        var current = start
        while (current.next != null) {
            if (current.next?.phones?.contains(phone) == true) return false
            if (current.next?.name == name) guy = current.next
            current = current.next!!
        }
        if (guy != null) {
            if (guy.phones.contains(phone)) return false
            guy.phones += phone
            return true
        }
        return false
    }

    /**
     * Убрать номер телефона.
     * Возвращает true, если номер был успешно удалён,
     * и false, если человек с таким именем отсутствовал в телефонной книге
     * либо у него не было такого номера телефона.
     */
    fun removePhone(name: String, phone: String): Boolean {
        val start = start ?: return false
        if (start.name == name && start.phones.contains(phone)) {
            start.phones = start.phones.minus(phone)
            return true
        }
        var current = start
        while (current.next != null) {
            if (current.next?.name == name && current.next?.phones!!.contains(phone)) {
                current.next?.phones = current.next?.phones?.minus(phone)!!
                return true
            }
            current = current.next!!
        }
        return false
    }

    /**
     * Вернуть все номера телефона заданного человека.
     * Если этого человека нет в книге, вернуть пустой список
     */
    fun phones(name: String): Set<String> {
        val start = start ?: return emptySet()
        if (start.name == name) return start.phones
        else {
            var current = start
            while (current.next != null) {
                if (current.next?.name == name) return current.next!!.phones
                current = current.next!!
            }
            return emptySet()
        }
    }

    /**
     * Вернуть имя человека по заданному номеру телефона.
     * Если такого номера нет в книге, вернуть null.
     */
    fun humanByPhone(phone: String): String? {
        val start = start ?: return null
        if (start.phones.contains(phone)) return start.name
        else {
            var current = start
            while (current.next != null) {
                if (current.next?.phones!!.contains(phone)) return current.next?.name
                current = current.next!!
            }
            return null
        }
    }

    /**
     * Две телефонные книги равны, если в них хранится одинаковый набор людей,
     * и каждому человеку соответствует одинаковый набор телефонов.
     * Порядок людей / порядок телефонов в книге не должен иметь значения.
     */
    override fun equals(other: Any?): Boolean {
        if (other !is PhoneBook) return false
        if (other === this) return true
        if (other.start == null && this.start == null) return true
        if (other.size != this.size) return false
        var current = start
        while (current != null) {
            var state = false
            var otherCurrent = other.start
            while (otherCurrent != null && !state) {
                if (current.name == otherCurrent.name && current.phones == otherCurrent.phones) {
                    state = true
                    break
                }
                otherCurrent = otherCurrent.next
            }
            if (!state) return false
            current = current.next
        }
        return true
    }

    override fun hashCode(): Int {
        return size
    }

}