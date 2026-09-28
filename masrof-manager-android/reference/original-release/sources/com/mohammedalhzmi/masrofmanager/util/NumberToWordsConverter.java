package com.mohammedalhzmi.masrofmanager.util;

import androidx.compose.animation.core.AnimationKt;
import com.google.firebase.firestore.model.Values;
import io.ktor.sse.ServerSentEventKt;
import kotlin.Metadata;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: NumberToWordsConverter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fJ\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J(\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0006H\u0002R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\u0014"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/util/NumberToWordsConverter;", "", "<init>", "()V", "ones", "", "", "[Ljava/lang/String;", "tens", "teens", "convert", "amount", "", "groupToWords", Values.VECTOR_MAP_VECTORS_KEY, "", "groupWithScale", "scale", "singular", "plural", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final class NumberToWordsConverter {
    public static final NumberToWordsConverter INSTANCE = new NumberToWordsConverter();
    private static final String[] ones = {"", "واحد", "اثنان", "ثلاثة", "أربعة", "خمسة", "ستة", "سبعة", "ثمانية", "تسعة"};
    private static final String[] tens = {"", "", "عشرون", "ثلاثون", "أربعون", "خمسون", "ستون", "سبعون", "ثمانون", "تسعون"};
    private static final String[] teens = {"عشرة", "أحد عشر", "اثنا عشر", "ثلاثة عشر", "أربعة عشر", "خمسة عشر", "ستة عشر", "سبعة عشر", "ثمانية عشر", "تسعة عشر"};
    public static final int $stable = 8;

    private NumberToWordsConverter() {
    }

    public final String convert(double amount) {
        StringBuilder sbAppend;
        String str;
        long jCoerceAtLeast = RangesKt.coerceAtLeast((long) amount, 0L);
        int iCoerceIn = RangesKt.coerceIn((int) ((amount - jCoerceAtLeast) * 100.0d), 0, 99);
        String strGroupToWords = jCoerceAtLeast == 0 ? "صفر" : groupToWords(jCoerceAtLeast);
        if (iCoerceIn == 0) {
            sbAppend = new StringBuilder().append(strGroupToWords);
            str = " ريال يمني فقط لا غير";
        } else {
            sbAppend = new StringBuilder().append(strGroupToWords).append(" ريال و").append(groupToWords(iCoerceIn));
            str = " فلس يمني فقط لا غير";
        }
        return sbAppend.append(str).toString();
    }

    private final String groupToWords(long value) {
        if (value < 10) {
            return ones[(int) value];
        }
        if (value < 20) {
            return teens[(int) (value - 10)];
        }
        if (value < 100) {
            long j = value % 10;
            if (j == 0) {
                return tens[(int) (value / 10)];
            }
            return ones[(int) j] + " و" + tens[(int) (value / 10)];
        }
        if (value >= 1000) {
            if (value < AnimationKt.MillisToNanos) {
                return groupWithScale(value, 1000L, "ألف", "آلاف");
            }
            if (value < 1000000000) {
                return groupWithScale(value, AnimationKt.MillisToNanos, "مليون", "ملايين");
            }
            return groupWithScale(value, 1000000000L, "مليار", "مليارات");
        }
        long j2 = value % 100;
        if (j2 == 0) {
            return ones[(int) (value / 100)] + " مائة";
        }
        return ones[(int) (value / 100)] + " مائة و" + groupToWords(j2);
    }

    private final String groupWithScale(long value, long scale, String singular, String plural) {
        StringBuilder sbAppend;
        StringBuilder sbAppend2;
        long j = value / scale;
        long j2 = value % scale;
        if (j != 1) {
            if (j == 2) {
                sbAppend = new StringBuilder("اثنان ");
            } else {
                if (3 > j || j >= 11) {
                    sbAppend = new StringBuilder().append(groupToWords(j)).append(ServerSentEventKt.SPACE);
                } else {
                    sbAppend2 = new StringBuilder().append(groupToWords(j)).append(ServerSentEventKt.SPACE).append(plural);
                }
                singular = sbAppend2.toString();
            }
            sbAppend2 = sbAppend.append(singular);
            singular = sbAppend2.toString();
        }
        if (j2 == 0) {
            return singular;
        }
        return singular + " و" + groupToWords(j2);
    }
}
