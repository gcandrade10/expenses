package com.nominalista.expenses.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Suppress("unused")

@Parcelize
enum class Currency(
    val title: String,
    val symbol: String,
    val flag: String
) : Parcelable {

    AED("Emirati Dirham", "د.إ", "\uD83C\uDDE6\uD83C\uDDEA"),
    AFN("Afghanistan Afghani", "؋", "\uD83C\uDDE6\uD83C\uDDEB"),
    ALL("Albanian Lek", "L", "\uD83C\uDDE6\uD83C\uDDF1"),
    AMD("Armenian Dram", "֏", "\uD83C\uDDE6\uD83C\uDDF2"),
    AOA("Angolan Kwanza", "Kz", "\uD83C\uDDE6\uD83C\uDDF4"),
    ARS("Argentine Peso", "$", "\uD83C\uDDE6\uD83C\uDDF7"),
    AUD("Australian Dollar", "$", "\uD83C\uDDE6\uD83C\uDDFA"),
    AZN("Azerbaijani Manat", "m", "\uD83C\uDDE6\uD83C\uDDFF"),
    BAM("Bosnia & Herzegovina Convertible Mark", "KM", "\uD83C\uDDE7\uD83C\uDDE6"),
    BBD("Barbadian Dollar", "$", "\uD83C\uDDE7\uD83C\uDDE7"),
    BDT("Bangladeshi Taka", "Tk", "\uD83C\uDDE7\uD83C\uDDE9"),
    BGN("Bulgarian Lev", "лв", "\uD83C\uDDE7\uD83C\uDDEC"),
    BHD("Bahraini Dinar", "BD", "\uD83C\uDDE7\uD83C\uDDED"),
    BIF("Burundian Franc", "FBu", "\uD83C\uDDE7\uD83C\uDDEE"),
    BMD("Bermuda Dollar", "$", "\uD83C\uDDE7\uD83C\uDDF2"),
    BND("Brunei Dollar", "$", "\uD83C\uDDE7\uD83C\uDDF3"),
    BOB("Bolivian Boliviano", "Bs", "\uD83C\uDDE7\uD83C\uDDF4"),
    BRL("Brazil Real", "R$", "\uD83C\uDDE7\uD83C\uDDF7"),
    BSD("Bahamian Dollar", "B$", "\uD83C\uDDE7\uD83C\uDDF8"),
    BTN("Bhutanese Ngultrum", "Nu.", "\uD83C\uDDE7\uD83C\uDDF9"),
    BWP("Botswana Pula", "P", "\uD83C\uDDE7\uD83C\uDDFC"),
    BYN("Belarusian Ruble", "Br", "\uD83C\uDDE7\uD83C\uDDFE"),
    BZD("Belize Dollar", "BZ$", "\uD83C\uDDE7\uD83C\uDDFF"),
    CAD("Canada Dollar", "$", "\uD83C\uDDE8\uD83C\uDDE6"),
    CDF("Congolese Franc", "FC", "\uD83C\uDDE8\uD83C\uDDE9"),
    CHF("Switzerland Franc", "CHF", "\uD83C\uDDE8\uD83C\uDDED"),
    CLP("Chile Peso", "$", "\uD83C\uDDE8\uD83C\uDDF1"),
    CNY("China Yuan", "¥", "\uD83C\uDDE8\uD83C\uDDF3"),
    COP("Colombia Peso", "$", "\uD83C\uDDE8\uD83C\uDDF4"),
    CRC("Costa Rica Colon", "₡", "\uD83C\uDDE8\uD83C\uDDF7"),
    CUP("Cuban Peso", "$", "\uD83C\uDDE8\uD83C\uDDFA"),
    CVE("Cape Verdean Escudo", "$", "\uD83C\uDDE8\uD83C\uDDFB"),
    CZK("Czech Koruna", "Kč", "\uD83C\uDDE8\uD83C\uDDFF"),
    DJF("Djiboutian Franc", "Fdj", "\uD83C\uDDE9\uD83C\uDDEF"),
    DKK("Denmark Krone", "kr", "\uD83C\uDDE9\uD83C\uDDF0"),
    DOP("Dominican Republic Peso", "RD$", "\uD83C\uDDE9\uD83C\uDDF4"),
    DZD("Algerian Dinar", "دج", "\uD83C\uDDE9\uD83C\uDDFF"),
    EGP("Egypt Pound", "£", "\uD83C\uDDEA\uD83C\uDDEC"),
    ERN("Eritrean Nakfa", "نافكا", "\uD83C\uDDEA\uD83C\uDDF7"),
    ETB("Ethiopian Birr", "Br", "\uD83C\uDDEA\uD83C\uDDF9"),
    EUR("Euro", "€", "\uD83C\uDDEA\uD83C\uDDFA"),
    FJB("Fijian Dollar", "FJ$", "\uD83C\uDDEB\uD83C\uDDEF"),
    GBP("British Pound", "£", "\uD83C\uDDEC\uD83C\uDDE7"),
    GEL("Georgian Lari", "₾", "\uD83C\uDDEC\uD83C\uDDEA"),
    GHS("Ghana Cedi", "¢", "\uD83C\uDDEC\uD83C\uDDED"),
    GMD("Gambian Dalasi", "D", "\uD83C\uDDEC\uD83C\uDDF2"),
    GNF("Guinean Franc", "FG", "\uD83C\uDDEC\uD83C\uDDF3"),
    GTQ("Guatemalan Quetzal", "Q", "\uD83C\uDDEC\uD83C\uDDF9"),
    GYD("Guyana Dollar", "$", "\uD83C\uDDEC\uD83C\uDDFE"),
    HKD("Hong Kong Dollar", "$", "\uD83C\uDDED\uD83C\uDDF0"),
    HNL("Honduran Lempira", "L", "\uD83C\uDDED\uD83C\uDDF3"),
    HRK("Croatia Kuna", "kn", "\uD83C\uDDED\uD83C\uDDF7"),
    HTG("Haitian Gourde", "G", "\uD83C\uDDED\uD83C\uDDF9"),
    HUF("Hungary Forint", "Ft", "\uD83C\uDDED\uD83C\uDDFA"),
    IDR("Indonesia Rupiah", "Rp", "\uD83C\uDDEE\uD83C\uDDE9"),
    ILS("Israel Shekel", "₪", "\uD83C\uDDEE\uD83C\uDDF1"),
    INR("India Rupee", "₹", "\uD83C\uDDEE\uD83C\uDDF3"),
    IQD("Iraqi Dinar", "ع.د", "\uD83C\uDDEE\uD83C\uDDF6"),
    IRR("Iranian Rial", "﷼", "\uD83C\uDDEE\uD83C\uDDF7"),
    ISK("Icelandic Króna", "kr", "\uD83C\uDDEE\uD83C\uDDF8"),
    JMD("Jamaica Dollar", "J$", "\uD83C\uDDEF\uD83C\uDDF2"),
    JOD("Jordanian Dinar", "د.ا", "\uD83C\uDDEF\uD83C\uDDF4"),
    JPY("Japanese Yen", "¥", "\uD83C\uDDEF\uD83C\uDDF5"),
    KES("Kenyan Shilling", "KSh", "\uD83C\uDDF0\uD83C\uDDEA"),
    KGS("Kyrgyzstani Som", "Лв", "\uD83C\uDDF0\uD83C\uDDEC"),
    KHR("Cambodian Riel", "៛", "\uD83C\uDDF0\uD83C\uDDED"),
    KMF("Comorian Franc", "CF", "\uD83C\uDDF0\uD83C\uDDF2"),
    KPW("North Korean Won", "₩", "\uD83C\uDDF0\uD83C\uDDF5"),
    KRW("South Korea Won", "₩", "\uD83C\uDDF0\uD83C\uDDF7"),
    KWD("Kuwaiti Dinar", "د.ك", "\uD83C\uDDF0\uD83C\uDDFC"),
    KYD("Cayman Islands Dollar", "$", "\uD83C\uDDF0\uD83C\uDDFE"),
    KZT("Kazakhstan Tenge", "лв", "\uD83C\uDDF0\uD83C\uDDFF"),
    LAK("Laos Kip", "₭", "\uD83C\uDDF1\uD83C\uDDE6"),
    LBP("Lebanese Pound", "ل.ل.", "\uD83C\uDDF1\uD83C\uDDE7"),
    LKR("Sri Lanka Rupee", "₨", "\uD83C\uDDF1\uD83C\uDDF0"),
    LRD("Liberia Dollar", "$", "\uD83C\uDDF1\uD83C\uDDF7"),
    LSL("Lesotho Loti", "M", "\uD83C\uDDF1\uD83C\uDDF8"),
    LTL("Lithuanian Litas", "Lt", "\uD83C\uDDF1\uD83C\uDDF9"),
    LYD("Libyan Dinar", "ل.د", "\uD83C\uDDF1\uD83C\uDDFE"),
    MAD("Moroccan Dirham", "MAD", "\uD83C\uDDF2\uD83C\uDDE6"),
    MDL("Moldovan Leu", "MDL", "\uD83C\uDDF2\uD83C\uDDE9"),
    MGA("Malagasy Ariary", "Ar", "\uD83C\uDDF2\uD83C\uDDEC"),
    MKD("Macedonia Denar", "ден", "\uD83C\uDDF2\uD83C\uDDF0"),
    MMK("Burmese Kyat", "K", "\uD83C\uDDF2\uD83C\uDDF2"),
    MNT("Mongolia Tughrik", "₮", "\uD83C\uDDF2\uD83C\uDDF3"),
    MRO("Mauritanian Ouguiya", "UM", "\uD83C\uDDF2\uD83C\uDDF7"),
    MUR("Mauritius Rupee", "₨", "\uD83C\uDDF2\uD83C\uDDF7"),
    MVR("Maldivian Rufiyaa", "Rf", "\uD83C\uDDF2\uD83C\uDDFB"),
    MWK("Malawian Kwacha", "MK", "\uD83C\uDDF2\uD83C\uDDFC"),
    MXN("Mexico Peso", "$", "\uD83C\uDDF2\uD83C\uDDFD"),
    MYR("Malaysia Ringgit", "RM", "\uD83C\uDDF2\uD83C\uDDFE"),
    MZN("Mozambique Metical", "MT", "\uD83C\uDDF2\uD83C\uDDFF"),
    NAD("Namibia Dollar", "$", "\uD83C\uDDF3\uD83C\uDDE6"),
    NGN("Nigeria Naira", "₦", "\uD83C\uDDF3\uD83C\uDDEC"),
    NIO("Nicaragua Cordoba", "C$", "\uD83C\uDDF3\uD83C\uDDEE"),
    NOK("Norway Krone", "kr", "\uD83C\uDDF3\uD83C\uDDF4"),
    NPR("Nepal Rupee", "₨", "\uD83C\uDDF3\uD83C\uDDF5"),
    NZD("New Zealand Dollar", "$", "\uD83C\uDDF3\uD83C\uDDFF"),
    OMR("Oman Rial", "﷼", "\uD83C\uDDF4\uD83C\uDDF2"),
    PAB("Panamanian Balboa", "B/.", "\uD83C\uDDF5\uD83C\uDDE6"),
    PEN("Peru Sol", "S/.", "\uD83C\uDDF5\uD83C\uDDEA"),
    PGK("Papua New Guinean Kina", "K", "\uD83C\uDDF5\uD83C\uDDEC"),
    PHP("Philippines Peso", "₱", "\uD83C\uDDF5\uD83C\uDDED"),
    PKR("Pakistan Rupee", "₨", "\uD83C\uDDF5\uD83C\uDDF0"),
    PLN("Polski zloty", "zł", "\uD83C\uDDF5\uD83C\uDDF1"),
    PYG("Paraguay Guarani", "Gs", "\uD83C\uDDF5\uD83C\uDDFE"),
    QAR("Qatar Riyal", "﷼", "\uD83C\uDDF6\uD83C\uDDE6"),
    RON("Romania Leu", "lei", "\uD83C\uDDF7\uD83C\uDDF4"),
    RSD("Serbia Dinar", "Дин.", "\uD83C\uDDF7\uD83C\uDDF8"),
    RUB("Russia Ruble", "₽", "\uD83C\uDDF7\uD83C\uDDFA"),
    RWF("Rwandan Franc", "FRw", "\uD83C\uDDF7\uD83C\uDDFC"),
    SAR("Saudi Arabia Riyal", "﷼", "\uD83C\uDDF8\uD83C\uDDE6"),
    SBD("Solomon Islands Dollar", "Si$", "\uD83C\uDDF8\uD83C\uDDE7"),
    SCR("Seychellois Rupee", "SR", "\uD83C\uDDF8\uD83C\uDDE8"),
    SDG("Sudanese Pound", "ج.س.", "\uD83C\uDDF8\uD83C\uDDE9"),
    SEK("Sweden Krona", "kr", "\uD83C\uDDF8\uD83C\uDDEA"),
    SGD("Singapore Dollar", "$", "\uD83C\uDDF8\uD83C\uDDEC"),
    SLL("Sierra Leonean Leone", "Le", "\uD83C\uDDF8\uD83C\uDDF1"),
    SOS("Somalia Shilling", "S", "\uD83C\uDDF8\uD83C\uDDF4"),
    SRD("Suriname Dollar", "$", "\uD83C\uDDF8\uD83C\uDDF7"),
    SSP("South Sudanese Pound", "£", "\uD83C\uDDF8\uD83C\uDDF8"),
    STD("São Tomé & Príncipe Dobra", "Db", "\uD83C\uDDF8\uD83C\uDDF9"),
    SYP("Syrian Pound", "LS", "\uD83C\uDDF8\uD83C\uDDFE"),
    SZL("Swazi Lilangeni", "E", "\uD83C\uDDF8\uD83C\uDDFF"),
    THB("Thailand Baht", "฿", "\uD83C\uDDF9\uD83C\uDDED"),
    TJS("Tajikistani Somoni", "ЅM", "\uD83C\uDDF9\uD83C\uDDEF"),
    TMT("Turkmenistan Manat", "T", "\uD83C\uDDF9\uD83C\uDDF2"),
    TND("Tunisian Dinar", "د.ت", "\uD83C\uDDF9\uD83C\uDDF3"),
    TOP("Tongan Pa'anga", "PT", "\uD83C\uDDF9\uD83C\uDDF4"),
    TRY("Turkish Lira", "₺", "\uD83C\uDDF9\uD83C\uDDF7"),
    TTD("Trinidad and Tobago Dollar", "TT$", "\uD83C\uDDF9\uD83C\uDDF9"),
    TWD("Taiwan New Dollar", "NT$", "\uD83C\uDDF9\uD83C\uDDFC"),
    TZS("Tanzanian Shilling", "TSh", "\uD83C\uDDF9\uD83C\uDDFF"),
    UAH("Ukraine Hryvnia", "₴", "\uD83C\uDDFA\uD83C\uDDE6"),
    UGX("Ugandan Shilling", "USh", "\uD83C\uDDFA\uD83C\uDDEC"),
    USD("United States Dollar", "$", "\uD83C\uDDFA\uD83C\uDDF8"),
    UYU("Uruguay Peso", "$", "\uD83C\uDDFA\uD83C\uDDFE"),
    UZS("Uzbekistani Som", "so'm", "\uD83C\uDDFA\uD83C\uDDFF"),
    VEF("Venezuela Bolívar", "Bs", "\uD83C\uDDFB\uD83C\uDDEA"),
    VND("Vietnamese Dong", "₫", "\uD83C\uDDFB\uD83C\uDDF3"),
    VUV("Vanuatu Vatu", "VT", "\uD83C\uDDFB\uD83C\uDDFA"),
    WST("Samoan Tala", "SAT", "\uD83C\uDDFC\uD83C\uDDF8"),
    XAF("Central African CFA Franc", "FCFA", "\uD83C\uDF0D"),
    XCD("East Caribbean Dollar", "$", "\uD83C\uDF0E"),
    XOF("West African CFA Franc", "CFA", "\uD83C\uDF0D"),
    YER("Yemen Rial", "﷼", "\uD83C\uDDFE\uD83C\uDDEA"),
    ZAR("South Africa Rand", "R", "🇿🇦"),
    ZMW("Zambian Kwacha", "ZK", "\uD83C\uDDFF\uD83C\uDDF2");

    val code: String
        get() = name

    companion object {

        fun fromCode(code: String): Currency? {
            return values().firstOrNull { currency -> currency.code == code }
        }
    }
}