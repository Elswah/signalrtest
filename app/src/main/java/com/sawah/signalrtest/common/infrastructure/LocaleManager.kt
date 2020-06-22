package com.sawah.signalrtest.common.infrastructure

/*object LocaleManager {
    val LANGUAGE_ENGLISH = "en"
    val LANGUAGE_ARABIC = "ar"
    private val PREFERENCE_DEVICE_LANGUAGE = "language_key"

    fun onAttach(context: Context): Context {
        val lang = getLanguagePref(context)
        return setLocale(context, lang)
    }

    fun setLocale(context: Context, language: String): Context {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            updateResources(context, language)
        } else updateResourcesLegacy(context, language)

    }




    @TargetApi(Build.VERSION_CODES.N)
    private fun updateResources(context: Context, language: String): Context {
        var localeLanguage = ""
        localeLanguage = if (language.equals(LANGUAGE_ENGLISH, true)) {
            LANGUAGE_ENGLISH
        } else {
            LANGUAGE_ARABIC
        }
        val locale = Locale(localeLanguage)
        Locale.setDefault(locale)

        val configuration = context.resources.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)

        return context.createConfigurationContext(configuration)
    }

    private fun updateResourcesLegacy(context: Context, language: String): Context {
        var localeLanguage = ""
        localeLanguage = if (language.equals(LANGUAGE_ENGLISH, true)) {
            LANGUAGE_ENGLISH
        } else {
            LANGUAGE_ARABIC
        }
        val locale = Locale(localeLanguage)
        Locale.setDefault(locale)
        val resources = context.resources

        val configuration = resources.configuration
        configuration.locale = locale
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            configuration.setLayoutDirection(locale)
        }

        resources.updateConfiguration(configuration, resources.displayMetrics)

        return context
    }
}*/
