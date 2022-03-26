package com.codequark.superhero.retrofit;

import androidx.annotation.NonNull;

import java.io.File;

public class Endpoints {
    private static final String BASE_URL = "https://codequark.com:8443";
    private static final String BASE_PATH = "/harper/api/requests";

    @NonNull
    public static String getBaseUrl() {
        return BASE_URL;
    }

    @NonNull
    public static String getPath(@NonNull String webService) {
        return BASE_PATH + webService;
    }

    public static final String AREA_TAG = "Area";
    public static final String ENCUESTA_TAG = "Encuesta";
    public static final String ENCUESTADO_TAG = "Encuestado";
    public static final String ESTADO_REPUBLICA_TAG = "EstadoRepublica";
    public static final String INSTITUCION_TAG = "Institucion";
    public static final String OPCION_TAG = "Opcion";
    public static final String PREGUNTA_TAG = "Pregunta";
    public static final String RESPUESTA_TAG = "Respuesta";
    public static final String ROL_TAG = "Rol";
    public static final String SECCION_TAG = "Seccion";
    public static final String TIPO_OPCION_TAG = "TipoOpcion";
    public static final String TIPO_PREGUNTA_TAG = "TipoPregunta";
    public static final String USUARIO_TAG = "Usuario";
    public static final String AUTH_TAG = "Auth";

    public static final String AREA_URL = "/area";
    public static final String ENCUESTA_URL = "/encuesta";
    public static final String ENCUESTADO_URL = "/encuestado";
    public static final String ESTADO_REPUBLICA_URL = "/estadoRepublica";
    public static final String INSTITUCION_URL = "/institucion";
    public static final String OPCION_URL = "/opcion";
    public static final String PREGUNTA_URL = "/pregunta";
    public static final String RESPUESTA_URL = "/respuesta";
    public static final String ROL_URL = "/rol";
    public static final String SECCION_URL = "/seccion";
    public static final String TIPO_OPCION_URL = "/tipoOpcion";
    public static final String TIPO_PREGUNTA_URL = "/tipoPregunta";
    public static final String USUARIO_URL = "/usuario";
    public static final String AUTH_URL = "/auth";

    public static final String areaUrl = BASE_PATH + AREA_URL;
    public static final String encuestadoUrl = BASE_PATH + ENCUESTADO_URL;
    public static final String encuestaUrl = BASE_PATH + ENCUESTA_URL;
    public static final String estadoRepublicaUrl = BASE_PATH + ESTADO_REPUBLICA_URL;
    public static final String institucionUrl = BASE_PATH + INSTITUCION_URL;
    public static final String opcionUrl = BASE_PATH + OPCION_URL;
    public static final String preguntaUrl = BASE_PATH + PREGUNTA_URL;
    public static final String respuestaUrl = BASE_PATH + RESPUESTA_URL;
    public static final String rolUrl = BASE_PATH + ROL_URL;
    public static final String seccionUrl = BASE_PATH + SECCION_URL;
    public static final String tipoOpcionUrl = BASE_PATH + TIPO_OPCION_URL;
    public static final String tipoPreguntaUrl = BASE_PATH + TIPO_PREGUNTA_URL;
    public static final String usuarioUrl = BASE_PATH + USUARIO_URL;
    public static final String authUrl = BASE_PATH + AUTH_URL;

    public static final String harper = "https://harper.codequark.com";
    private static final String codequark = "https://www.codequark.com";

    public static final String playStoreUrl = "https://play.google.com/store/apps/details?id={package}";
    public static final String aboutPageApp = harper + File.separator;
    public static final String aboutPageCompany = codequark + File.separator;
}