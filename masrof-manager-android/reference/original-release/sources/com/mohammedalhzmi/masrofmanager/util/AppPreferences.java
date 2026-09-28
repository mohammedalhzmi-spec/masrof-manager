package com.mohammedalhzmi.masrofmanager.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import com.google.firebase.firestore.model.Values;
import com.mohammedalhzmi.masrofmanager.data.DocumentType;
import io.ktor.http.LinkHeader;
import java.io.InputStream;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.p012io.CloseableKt;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: AppPreferences.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0006\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u00072\u0006\u0010\t\u001a\u00020\nH\u0002J \u0010\u000b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u0005J\u001e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005J\u000e\u0010\u0011\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u0012\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u0013\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u0014\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u0015\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nJ\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0018J\u001e\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001bJ\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0018J\u0016\u0010\u001e\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0018J\u001e\u0010\u001f\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0010\u001a\u00020\u0005J\u0016\u0010 \u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0018J\u001e\u0010!\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0010\u001a\u00020\u0005J\u0018\u0010\"\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0018J\u001e\u0010#\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001bJ\u0016\u0010$\u001a\u00020%2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0018J\u001e\u0010&\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0010\u001a\u00020%J\u0016\u0010'\u001a\u00020%2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0018J\u001e\u0010(\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0010\u001a\u00020%J\u0016\u0010)\u001a\u00020%2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0018J\u0016\u0010*\u001a\u00020%2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0018J&\u0010+\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010,\u001a\u00020%2\u0006\u0010-\u001a\u00020%J\u0016\u0010.\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0018J\u001e\u0010/\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0010\u001a\u00020\u0005J\u0016\u00100\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0018J\u001e\u00101\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0010\u001a\u00020\u0005J\u0016\u00102\u001a\u0002032\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0018J\u001e\u00104\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0010\u001a\u000203J\u0016\u00105\u001a\u0002032\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0018J\u001e\u00106\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0010\u001a\u000203J\u0016\u00107\u001a\u0002032\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0018J\u001e\u00108\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0010\u001a\u000203R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u00069"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/util/AppPreferences;", "", "<init>", "()V", "FILE", "", "prefs", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "context", "Landroid/content/Context;", "get", "key", "fallback", "put", "", Values.VECTOR_MAP_VECTORS_KEY, "ministry", "administration", "branch", "manager", "financeManager", "logoUri", LinkHeader.Parameters.Type, "Lcom/mohammedalhzmi/masrofmanager/data/DocumentType;", "saveLogo", "uri", "Landroid/net/Uri;", "loadLogo", "Landroid/graphics/Bitmap;", "pageSize", "setPageSize", "backgroundColor", "setBackgroundColor", "backgroundImageUri", "saveBackgroundImage", "backgroundOpacity", "", "setBackgroundOpacity", "backgroundScale", "setBackgroundScale", "backgroundOffsetX", "backgroundOffsetY", "setBackgroundOffset", "x", "y", "textColor", "setTextColor", "fontFamily", "setFontFamily", "textBold", "", "setTextBold", "textItalic", "setTextItalic", "textUnderline", "setTextUnderline", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final class AppPreferences {
    public static final int $stable = 0;
    private static final String FILE = "masrof_preferences";
    public static final AppPreferences INSTANCE = new AppPreferences();

    private AppPreferences() {
    }

    private final SharedPreferences prefs(Context context) {
        return context.getSharedPreferences(FILE, 0);
    }

    public static /* synthetic */ String get$default(AppPreferences appPreferences, Context context, String str, String str2, int i, Object obj) {
        if ((i & 4) != 0) {
            str2 = "";
        }
        return appPreferences.get(context, str, str2);
    }

    public final String get(Context context, String key, String fallback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(fallback, "fallback");
        String string = prefs(context).getString(key, fallback);
        return string == null ? "" : string;
    }

    public final void put(Context context, String key, String value) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        prefs(context).edit().putString(key, value).apply();
    }

    public final String ministry(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return get(context, "ministry", "وزارة الإدارة والتنمية المحلية والريفية");
    }

    public final String administration(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return get(context, "administration", "صندوق النظافة والتحسين");
    }

    public final String branch(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return get(context, "branch", "فرع المديرية");
    }

    public final String manager(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return get(context, "manager", "مدير الفرع");
    }

    public final String financeManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return get(context, "finance_manager", "المدير المالي");
    }

    public final String logoUri(Context context, DocumentType type) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(type, "type");
        String lowerCase = type.name().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String str = get$default(this, context, "logo_" + lowerCase, null, 4, null);
        if (StringsKt.isBlank(str)) {
            str = null;
        }
        return str;
    }

    public final void saveLogo(Context context, DocumentType type, Uri uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(uri, "uri");
        String lowerCase = type.name().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String string = uri.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        put(context, "logo_" + lowerCase, string);
    }

    public final Bitmap loadLogo(Context context, DocumentType type) {
        Object objM7781constructorimpl;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(type, "type");
        String strLogoUri = logoUri(context, type);
        if (strLogoUri == null) {
            return null;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            InputStream inputStreamOpenInputStream = context.getContentResolver().openInputStream(Uri.parse(strLogoUri));
            try {
                Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpenInputStream);
                CloseableKt.closeFinally(inputStreamOpenInputStream, null);
                objM7781constructorimpl = Result.m7781constructorimpl(bitmapDecodeStream);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(inputStreamOpenInputStream, th);
                    throw th2;
                }
            }
        } catch (Throwable th3) {
            Result.Companion companion2 = Result.INSTANCE;
            objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th3));
        }
        return (Bitmap) (Result.m7787isFailureimpl(objM7781constructorimpl) ? null : objM7781constructorimpl);
    }

    public final String pageSize(Context context, DocumentType type) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(type, "type");
        return get(context, "page_size_" + type.name(), type == DocumentType.ORDER ? "HALF_A4" : "A4");
    }

    public final void setPageSize(Context context, DocumentType type, String value) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(value, "value");
        put(context, "page_size_" + type.name(), value);
    }

    public final String backgroundColor(Context context, DocumentType type) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(type, "type");
        return get(context, "background_" + type.name(), "#FFFFFF");
    }

    public final void setBackgroundColor(Context context, DocumentType type, String value) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(value, "value");
        put(context, "background_" + type.name(), value);
    }

    public final String backgroundImageUri(Context context, DocumentType type) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(type, "type");
        String str = get$default(this, context, "background_image_" + type.name(), null, 4, null);
        if (StringsKt.isBlank(str)) {
            str = null;
        }
        return str;
    }

    public final void saveBackgroundImage(Context context, DocumentType type, Uri uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(uri, "uri");
        String str = "background_image_" + type.name();
        String string = uri.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        put(context, str, string);
    }

    public final float backgroundOpacity(Context context, DocumentType type) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(type, "type");
        Float floatOrNull = StringsKt.toFloatOrNull(get(context, "background_opacity_" + type.name(), "0.10"));
        if (floatOrNull != null) {
            return RangesKt.coerceIn(floatOrNull.floatValue(), 0.0f, 1.0f);
        }
        return 0.1f;
    }

    public final void setBackgroundOpacity(Context context, DocumentType type, float value) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(type, "type");
        put(context, "background_opacity_" + type.name(), String.valueOf(RangesKt.coerceIn(value, 0.0f, 1.0f)));
    }

    public final float backgroundScale(Context context, DocumentType type) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(type, "type");
        Float floatOrNull = StringsKt.toFloatOrNull(get(context, "background_scale_" + type.name(), "1.0"));
        if (floatOrNull != null) {
            return RangesKt.coerceIn(floatOrNull.floatValue(), 0.2f, 3.0f);
        }
        return 1.0f;
    }

    public final void setBackgroundScale(Context context, DocumentType type, float value) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(type, "type");
        put(context, "background_scale_" + type.name(), String.valueOf(RangesKt.coerceIn(value, 0.2f, 3.0f)));
    }

    public final float backgroundOffsetX(Context context, DocumentType type) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(type, "type");
        Float floatOrNull = StringsKt.toFloatOrNull(get(context, "background_x_" + type.name(), "0"));
        if (floatOrNull != null) {
            return floatOrNull.floatValue();
        }
        return 0.0f;
    }

    public final float backgroundOffsetY(Context context, DocumentType type) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(type, "type");
        Float floatOrNull = StringsKt.toFloatOrNull(get(context, "background_y_" + type.name(), "0"));
        if (floatOrNull != null) {
            return floatOrNull.floatValue();
        }
        return 0.0f;
    }

    public final void setBackgroundOffset(Context context, DocumentType type, float x, float y) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(type, "type");
        put(context, "background_x_" + type.name(), String.valueOf(x));
        put(context, "background_y_" + type.name(), String.valueOf(y));
    }

    public final String textColor(Context context, DocumentType type) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(type, "type");
        return get(context, "text_color_" + type.name(), "#000000");
    }

    public final void setTextColor(Context context, DocumentType type, String value) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(value, "value");
        put(context, "text_color_" + type.name(), value);
    }

    public final String fontFamily(Context context, DocumentType type) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(type, "type");
        return get(context, "font_family_" + type.name(), "SANS");
    }

    public final void setFontFamily(Context context, DocumentType type, String value) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(value, "value");
        put(context, "font_family_" + type.name(), value);
    }

    public final boolean textBold(Context context, DocumentType type) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(type, "type");
        return Intrinsics.areEqual(get(context, "text_bold_" + type.name(), "false"), "true");
    }

    public final void setTextBold(Context context, DocumentType type, boolean value) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(type, "type");
        put(context, "text_bold_" + type.name(), String.valueOf(value));
    }

    public final boolean textItalic(Context context, DocumentType type) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(type, "type");
        return Intrinsics.areEqual(get(context, "text_italic_" + type.name(), "false"), "true");
    }

    public final void setTextItalic(Context context, DocumentType type, boolean value) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(type, "type");
        put(context, "text_italic_" + type.name(), String.valueOf(value));
    }

    public final boolean textUnderline(Context context, DocumentType type) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(type, "type");
        return Intrinsics.areEqual(get(context, "text_underline_" + type.name(), "false"), "true");
    }

    public final void setTextUnderline(Context context, DocumentType type, boolean value) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(type, "type");
        put(context, "text_underline_" + type.name(), String.valueOf(value));
    }
}
