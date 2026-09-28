package com.mohammedalhzmi.masrofmanager.util;

import androidx.compose.material3.MenuKt;
import com.google.common.net.HttpHeaders;
import com.mohammedalhzmi.masrofmanager.data.DesignElementEntity;
import com.mohammedalhzmi.masrofmanager.data.DocumentDesignEntity;
import io.ktor.http.LinkHeader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.collections.IntIterator;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.p014io.files.FileSystemKt;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: AiLayoutAssistant.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001\u0017B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JD\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00052\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0086@¢\u0006\u0002\u0010\u000fJ\u0014\u0010\u0010\u001a\u0004\u0018\u00010\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0002J\u001b\u0010\u0013\u001a\u0004\u0018\u00010\u0014*\u00020\u00122\u0006\u0010\u0015\u001a\u00020\bH\u0002¢\u0006\u0002\u0010\u0016¨\u0006\u0018"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/util/AiLayoutAssistant;", "", "<init>", "()V", "plan", "", "Lcom/mohammedalhzmi/masrofmanager/util/AiLayoutAssistant$Command;", "apiKey", "", "endpoint", "instruction", "elements", "Lcom/mohammedalhzmi/masrofmanager/data/DesignElementEntity;", "design", "Lcom/mohammedalhzmi/masrofmanager/data/DocumentDesignEntity;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lcom/mohammedalhzmi/masrofmanager/data/DocumentDesignEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "parse", "o", "Lorg/json/JSONObject;", "floatOrNull", "", "key", "(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/Float;", "Command", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final class AiLayoutAssistant {
    public static final int $stable = 0;
    public static final AiLayoutAssistant INSTANCE = new AiLayoutAssistant();

    /* JADX INFO: compiled from: AiLayoutAssistant.kt */
    @Metadata(m913d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b8\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BÏ\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0016\u0010\u0017J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\u0010\u0010/\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u001bJ\u000b\u00100\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u00102\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010 J\u0010\u00103\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010 J\u0010\u00104\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010 J\u0010\u00105\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010 J\u0010\u00106\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010 J\u0010\u00107\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010 J\u000b\u00108\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010:\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010 J\u0010\u0010;\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010 J\u000b\u0010<\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010=\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010 J\u000b\u0010>\u001a\u0004\u0018\u00010\u0003HÆ\u0003JØ\u0001\u0010?\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010@J\u0013\u0010A\u001a\u00020B2\b\u0010C\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010D\u001a\u00020EHÖ\u0001J\t\u0010F\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0019R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0019R\u0015\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010!\u001a\u0004\b\u001f\u0010 R\u0015\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010!\u001a\u0004\b\"\u0010 R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010!\u001a\u0004\b#\u0010 R\u0015\u0010\f\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010!\u001a\u0004\b$\u0010 R\u0015\u0010\r\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010!\u001a\u0004\b%\u0010 R\u0015\u0010\u000e\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010!\u001a\u0004\b&\u0010 R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0019R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0019R\u0015\u0010\u0011\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010!\u001a\u0004\b)\u0010 R\u0015\u0010\u0012\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010!\u001a\u0004\b*\u0010 R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0019R\u0015\u0010\u0014\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010!\u001a\u0004\b,\u0010 R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u0019¨\u0006G"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/util/AiLayoutAssistant$Command;", "", "action", "", "targetId", "", LinkHeader.Parameters.Type, "content", "x", "", "y", "width", "height", "rotation", "fontSize", "textColor", "textAlign", "pageWidth", "pageHeight", "orientation", "margin", "backgroundColor", "<init>", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/String;Ljava/lang/Float;Ljava/lang/String;)V", "getAction", "()Ljava/lang/String;", "getTargetId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getType", "getContent", "getX", "()Ljava/lang/Float;", "Ljava/lang/Float;", "getY", "getWidth", "getHeight", "getRotation", "getFontSize", "getTextColor", "getTextAlign", "getPageWidth", "getPageHeight", "getOrientation", "getMargin", "getBackgroundColor", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "copy", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/String;Ljava/lang/Float;Ljava/lang/String;)Lcom/mohammedalhzmi/masrofmanager/util/AiLayoutAssistant$Command;", "equals", "", "other", "hashCode", "", "toString", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
    public static final /* data */ class Command {
        public static final int $stable = 0;
        private final String action;
        private final String backgroundColor;
        private final String content;
        private final Float fontSize;
        private final Float height;
        private final Float margin;
        private final String orientation;
        private final Float pageHeight;
        private final Float pageWidth;
        private final Float rotation;
        private final Long targetId;
        private final String textAlign;
        private final String textColor;
        private final String type;
        private final Float width;
        private final Float x;
        private final Float y;

        public static /* synthetic */ Command copy$default(Command command, String str, Long l, String str2, String str3, Float f, Float f2, Float f3, Float f4, Float f5, Float f6, String str4, String str5, Float f7, Float f8, String str6, Float f9, String str7, int i, Object obj) {
            String str8;
            Float f10;
            String str9 = (i & 1) != 0 ? command.action : str;
            Long l2 = (i & 2) != 0 ? command.targetId : l;
            String str10 = (i & 4) != 0 ? command.type : str2;
            String str11 = (i & 8) != 0 ? command.content : str3;
            Float f11 = (i & 16) != 0 ? command.x : f;
            Float f12 = (i & 32) != 0 ? command.y : f2;
            Float f13 = (i & 64) != 0 ? command.width : f3;
            Float f14 = (i & 128) != 0 ? command.height : f4;
            Float f15 = (i & 256) != 0 ? command.rotation : f5;
            Float f16 = (i & 512) != 0 ? command.fontSize : f6;
            String str12 = (i & 1024) != 0 ? command.textColor : str4;
            String str13 = (i & 2048) != 0 ? command.textAlign : str5;
            Float f17 = (i & 4096) != 0 ? command.pageWidth : f7;
            Float f18 = (i & 8192) != 0 ? command.pageHeight : f8;
            String str14 = str9;
            String str15 = (i & 16384) != 0 ? command.orientation : str6;
            Float f19 = (i & 32768) != 0 ? command.margin : f9;
            if ((i & 65536) != 0) {
                f10 = f19;
                str8 = command.backgroundColor;
            } else {
                str8 = str7;
                f10 = f19;
            }
            return command.copy(str14, l2, str10, str11, f11, f12, f13, f14, f15, f16, str12, str13, f17, f18, str15, f10, str8);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getAction() {
            return this.action;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final Float getFontSize() {
            return this.fontSize;
        }

        /* JADX INFO: renamed from: component11, reason: from getter */
        public final String getTextColor() {
            return this.textColor;
        }

        /* JADX INFO: renamed from: component12, reason: from getter */
        public final String getTextAlign() {
            return this.textAlign;
        }

        /* JADX INFO: renamed from: component13, reason: from getter */
        public final Float getPageWidth() {
            return this.pageWidth;
        }

        /* JADX INFO: renamed from: component14, reason: from getter */
        public final Float getPageHeight() {
            return this.pageHeight;
        }

        /* JADX INFO: renamed from: component15, reason: from getter */
        public final String getOrientation() {
            return this.orientation;
        }

        /* JADX INFO: renamed from: component16, reason: from getter */
        public final Float getMargin() {
            return this.margin;
        }

        /* JADX INFO: renamed from: component17, reason: from getter */
        public final String getBackgroundColor() {
            return this.backgroundColor;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Long getTargetId() {
            return this.targetId;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getContent() {
            return this.content;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Float getX() {
            return this.x;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final Float getY() {
            return this.y;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final Float getWidth() {
            return this.width;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final Float getHeight() {
            return this.height;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final Float getRotation() {
            return this.rotation;
        }

        public final Command copy(String action, Long targetId, String type, String content, Float x, Float y, Float width, Float height, Float rotation, Float fontSize, String textColor, String textAlign, Float pageWidth, Float pageHeight, String orientation, Float margin, String backgroundColor) {
            Intrinsics.checkNotNullParameter(action, "action");
            return new Command(action, targetId, type, content, x, y, width, height, rotation, fontSize, textColor, textAlign, pageWidth, pageHeight, orientation, margin, backgroundColor);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Command)) {
                return false;
            }
            Command command = (Command) other;
            return Intrinsics.areEqual(this.action, command.action) && Intrinsics.areEqual(this.targetId, command.targetId) && Intrinsics.areEqual(this.type, command.type) && Intrinsics.areEqual(this.content, command.content) && Intrinsics.areEqual((Object) this.x, (Object) command.x) && Intrinsics.areEqual((Object) this.y, (Object) command.y) && Intrinsics.areEqual((Object) this.width, (Object) command.width) && Intrinsics.areEqual((Object) this.height, (Object) command.height) && Intrinsics.areEqual((Object) this.rotation, (Object) command.rotation) && Intrinsics.areEqual((Object) this.fontSize, (Object) command.fontSize) && Intrinsics.areEqual(this.textColor, command.textColor) && Intrinsics.areEqual(this.textAlign, command.textAlign) && Intrinsics.areEqual((Object) this.pageWidth, (Object) command.pageWidth) && Intrinsics.areEqual((Object) this.pageHeight, (Object) command.pageHeight) && Intrinsics.areEqual(this.orientation, command.orientation) && Intrinsics.areEqual((Object) this.margin, (Object) command.margin) && Intrinsics.areEqual(this.backgroundColor, command.backgroundColor);
        }

        public int hashCode() {
            int iHashCode = this.action.hashCode() * 31;
            Long l = this.targetId;
            int iHashCode2 = (iHashCode + (l == null ? 0 : l.hashCode())) * 31;
            String str = this.type;
            int iHashCode3 = (iHashCode2 + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.content;
            int iHashCode4 = (iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
            Float f = this.x;
            int iHashCode5 = (iHashCode4 + (f == null ? 0 : f.hashCode())) * 31;
            Float f2 = this.y;
            int iHashCode6 = (iHashCode5 + (f2 == null ? 0 : f2.hashCode())) * 31;
            Float f3 = this.width;
            int iHashCode7 = (iHashCode6 + (f3 == null ? 0 : f3.hashCode())) * 31;
            Float f4 = this.height;
            int iHashCode8 = (iHashCode7 + (f4 == null ? 0 : f4.hashCode())) * 31;
            Float f5 = this.rotation;
            int iHashCode9 = (iHashCode8 + (f5 == null ? 0 : f5.hashCode())) * 31;
            Float f6 = this.fontSize;
            int iHashCode10 = (iHashCode9 + (f6 == null ? 0 : f6.hashCode())) * 31;
            String str3 = this.textColor;
            int iHashCode11 = (iHashCode10 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.textAlign;
            int iHashCode12 = (iHashCode11 + (str4 == null ? 0 : str4.hashCode())) * 31;
            Float f7 = this.pageWidth;
            int iHashCode13 = (iHashCode12 + (f7 == null ? 0 : f7.hashCode())) * 31;
            Float f8 = this.pageHeight;
            int iHashCode14 = (iHashCode13 + (f8 == null ? 0 : f8.hashCode())) * 31;
            String str5 = this.orientation;
            int iHashCode15 = (iHashCode14 + (str5 == null ? 0 : str5.hashCode())) * 31;
            Float f9 = this.margin;
            int iHashCode16 = (iHashCode15 + (f9 == null ? 0 : f9.hashCode())) * 31;
            String str6 = this.backgroundColor;
            return iHashCode16 + (str6 != null ? str6.hashCode() : 0);
        }

        public String toString() {
            return "Command(action=" + this.action + ", targetId=" + this.targetId + ", type=" + this.type + ", content=" + this.content + ", x=" + this.x + ", y=" + this.y + ", width=" + this.width + ", height=" + this.height + ", rotation=" + this.rotation + ", fontSize=" + this.fontSize + ", textColor=" + this.textColor + ", textAlign=" + this.textAlign + ", pageWidth=" + this.pageWidth + ", pageHeight=" + this.pageHeight + ", orientation=" + this.orientation + ", margin=" + this.margin + ", backgroundColor=" + this.backgroundColor + ")";
        }

        public Command(String action, Long l, String str, String str2, Float f, Float f2, Float f3, Float f4, Float f5, Float f6, String str3, String str4, Float f7, Float f8, String str5, Float f9, String str6) {
            Intrinsics.checkNotNullParameter(action, "action");
            this.action = action;
            this.targetId = l;
            this.type = str;
            this.content = str2;
            this.x = f;
            this.y = f2;
            this.width = f3;
            this.height = f4;
            this.rotation = f5;
            this.fontSize = f6;
            this.textColor = str3;
            this.textAlign = str4;
            this.pageWidth = f7;
            this.pageHeight = f8;
            this.orientation = str5;
            this.margin = f9;
            this.backgroundColor = str6;
        }

        public /* synthetic */ Command(String str, Long l, String str2, String str3, Float f, Float f2, Float f3, Float f4, Float f5, Float f6, String str4, String str5, Float f7, Float f8, String str6, Float f9, String str7, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? null : l, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : f, (i & 32) != 0 ? null : f2, (i & 64) != 0 ? null : f3, (i & 128) != 0 ? null : f4, (i & 256) != 0 ? null : f5, (i & 512) != 0 ? null : f6, (i & 1024) != 0 ? null : str4, (i & 2048) != 0 ? null : str5, (i & 4096) != 0 ? null : f7, (i & 8192) != 0 ? null : f8, (i & 16384) != 0 ? null : str6, (i & 32768) != 0 ? null : f9, (i & 65536) != 0 ? null : str7);
        }

        public final String getAction() {
            return this.action;
        }

        public final Long getTargetId() {
            return this.targetId;
        }

        public final String getType() {
            return this.type;
        }

        public final String getContent() {
            return this.content;
        }

        public final Float getX() {
            return this.x;
        }

        public final Float getY() {
            return this.y;
        }

        public final Float getWidth() {
            return this.width;
        }

        public final Float getHeight() {
            return this.height;
        }

        public final Float getRotation() {
            return this.rotation;
        }

        public final Float getFontSize() {
            return this.fontSize;
        }

        public final String getTextColor() {
            return this.textColor;
        }

        public final String getTextAlign() {
            return this.textAlign;
        }

        public final Float getPageWidth() {
            return this.pageWidth;
        }

        public final Float getPageHeight() {
            return this.pageHeight;
        }

        public final String getOrientation() {
            return this.orientation;
        }

        public final Float getMargin() {
            return this.margin;
        }

        public final String getBackgroundColor() {
            return this.backgroundColor;
        }
    }

    private AiLayoutAssistant() {
    }

    public final Object plan(String str, String str2, String str3, List<DesignElementEntity> list, DocumentDesignEntity documentDesignEntity, Continuation<? super List<Command>> continuation) throws JSONException, IOException {
        String orientation;
        String backgroundColor;
        JSONArray jSONArrayOptJSONArray;
        JSONObject jSONObjectOptJSONObject;
        JSONObject jSONObjectOptJSONObject2;
        if (StringsKt.isBlank(str)) {
            throw new IllegalArgumentException("أدخل مفتاح الذكاء الاصطناعي".toString());
        }
        JSONArray jSONArray = new JSONArray();
        for (DesignElementEntity designElementEntity : list) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", designElementEntity.getId());
            jSONObject.put(LinkHeader.Parameters.Type, designElementEntity.getType());
            jSONObject.put("content", StringsKt.take(designElementEntity.getContent(), MenuKt.InTransitionDuration));
            jSONObject.put("x", Boxing.boxFloat(designElementEntity.getX()));
            jSONObject.put("y", Boxing.boxFloat(designElementEntity.getY()));
            jSONObject.put("width", Boxing.boxFloat(designElementEntity.getWidth()));
            jSONObject.put("height", Boxing.boxFloat(designElementEntity.getHeight()));
            jSONObject.put("zIndex", designElementEntity.getZIndex());
            jSONArray.put(jSONObject);
        }
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("width", documentDesignEntity != null ? Boxing.boxFloat(documentDesignEntity.getPageWidth()) : Boxing.boxInt(595));
        jSONObject2.put("height", documentDesignEntity != null ? Boxing.boxFloat(documentDesignEntity.getPageHeight()) : Boxing.boxInt(842));
        if (documentDesignEntity == null || (orientation = documentDesignEntity.getOrientation()) == null) {
            orientation = "PORTRAIT";
        }
        jSONObject2.put("orientation", orientation);
        jSONObject2.put("margin", documentDesignEntity != null ? Boxing.boxFloat(documentDesignEntity.getMarginLeft()) : Boxing.boxInt(25));
        if (documentDesignEntity == null || (backgroundColor = documentDesignEntity.getBackgroundColor()) == null) {
            backgroundColor = "#FFFFFF";
        }
        jSONObject2.put("backgroundColor", backgroundColor);
        String str4 = "الطلب: " + str3 + "\nالعناصر الحالية: " + jSONArray + "\nإعدادات الصفحة: " + jSONObject2;
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("model", "gpt-5-mini");
        jSONObject3.put("messages", new JSONArray().put(new JSONObject().put("role", "system").put("content", "أنت مساعد متخصص في تنسيق مستندات مالية عربية داخل محرر Canvas. مهمتك تحويل طلب المستخدم إلى أوامر تصميم فقط، وليس كتابة كود أو اتخاذ قرار مالي. أعد JSON صالحًا فقط بالشكل {\\\"commands\\\":[...]}. الأفعال المسموحة فقط: MOVE, RESIZE, ROTATE, UPDATE_TEXT, STYLE_TEXT, ADD_TEXT, ADD_SHAPE, ADD_QR, DELETE, PAGE. لا تغير المستخدمين أو الصلاحيات أو المبالغ أو السجلات المالية، ولا تحذف عنصرًا إلا إذا طلب المستخدم ذلك صراحة. استخدم targetId من قائمة العناصر فقط. الإحداثيات بنقاط الصفحة، والاتجاه PORTRAIT أو LANDSCAPE، والمحاذاة START أو CENTER أو END. مفاتيح الأمر المتاحة: action,targetId,type,content,x,y,width,height,rotation,fontSize,textColor,textAlign,pageWidth,pageHeight,orientation,margin,backgroundColor. عند الغموض أو عدم وجود هدف مطابق أعد commands فارغة. لا تخترع معرفات. ضع حدودًا معقولة: x/y بين 0 و10000، العرض/الارتفاع بين 10 و10000، وحجم الخط بين 6 و200. لا تعتمد على نفسك في الحسابات المالية أو التواريخ الرسمية.")).put(new JSONObject().put("role", "user").put("content", str4)));
        jSONObject3.put("temperature", 0.1d);
        jSONObject3.put("response_format", new JSONObject().put(LinkHeader.Parameters.Type, "json_object"));
        String string = jSONObject3.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        Response responseExecute = new OkHttpClient.Builder().callTimeout(45L, TimeUnit.SECONDS).build().newCall(new Request.Builder().url(StringsKt.trimEnd(str2, FileSystemKt.UnixPathSeparator)).addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + str).addHeader(HttpHeaders.CONTENT_TYPE, "application/json").post(RequestBody.INSTANCE.create(string, MediaType.INSTANCE.get("application/json"))).build()).execute();
        ResponseBody responseBodyBody = responseExecute.body();
        String strString = responseBodyBody != null ? responseBodyBody.string() : null;
        if (strString == null) {
            strString = "";
        }
        if (!responseExecute.isSuccessful()) {
            throw new IllegalStateException(("خدمة الذكاء الاصطناعي رفضت الطلب: " + responseExecute.code()).toString());
        }
        JSONArray jSONArrayOptJSONArray2 = new JSONObject(strString).optJSONArray("choices");
        String strOptString = (jSONArrayOptJSONArray2 == null || (jSONObjectOptJSONObject = jSONArrayOptJSONArray2.optJSONObject(0)) == null || (jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject("message")) == null) ? null : jSONObjectOptJSONObject2.optString("content");
        String string2 = StringsKt.trim((CharSequence) StringsKt.removeSuffix(StringsKt.removePrefix(StringsKt.removePrefix(StringsKt.trim((CharSequence) (strOptString != null ? strOptString : "")).toString(), (CharSequence) "```json"), (CharSequence) "```"), (CharSequence) "```")).toString();
        if (StringsKt.startsWith$default(string2, "[", false, 2, (Object) null)) {
            jSONArrayOptJSONArray = new JSONArray(string2);
        } else {
            jSONArrayOptJSONArray = new JSONObject(string2).optJSONArray("commands");
            if (jSONArrayOptJSONArray == null) {
                jSONArrayOptJSONArray = new JSONArray();
            }
        }
        IntRange intRangeUntil = RangesKt.until(0, jSONArrayOptJSONArray.length());
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = intRangeUntil.iterator();
        while (it.hasNext()) {
            Command command = INSTANCE.parse(jSONArrayOptJSONArray.optJSONObject(((IntIterator) it).nextInt()));
            if (command != null) {
                arrayList.add(command);
            }
        }
        return arrayList;
    }

    private final Command parse(JSONObject o) {
        if (o == null) {
            return null;
        }
        String strOptString = o.optString("action");
        Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
        String upperCase = strOptString.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        if (!SetsKt.setOf((Object[]) new String[]{"MOVE", "RESIZE", "ROTATE", "UPDATE_TEXT", "STYLE_TEXT", "ADD_TEXT", "ADD_SHAPE", "ADD_QR", "DELETE", "PAGE"}).contains(upperCase)) {
            return null;
        }
        Long lValueOf = Long.valueOf(o.optLong("targetId"));
        Long l = lValueOf.longValue() != 0 ? lValueOf : null;
        String strOptString2 = o.optString(LinkHeader.Parameters.Type);
        Intrinsics.checkNotNull(strOptString2);
        String str = !StringsKt.isBlank(strOptString2) ? strOptString2 : null;
        String strOptString3 = o.optString("content");
        Intrinsics.checkNotNull(strOptString3);
        String str2 = !StringsKt.isBlank(strOptString3) ? strOptString3 : null;
        Float fFloatOrNull = floatOrNull(o, "x");
        Float fFloatOrNull2 = floatOrNull(o, "y");
        Float fFloatOrNull3 = floatOrNull(o, "width");
        Float fFloatOrNull4 = floatOrNull(o, "height");
        Float fFloatOrNull5 = floatOrNull(o, "rotation");
        Float fFloatOrNull6 = floatOrNull(o, "fontSize");
        String strOptString4 = o.optString("textColor");
        Intrinsics.checkNotNull(strOptString4);
        String str3 = !StringsKt.isBlank(strOptString4) ? strOptString4 : null;
        String strOptString5 = o.optString("textAlign");
        Intrinsics.checkNotNull(strOptString5);
        String str4 = !StringsKt.isBlank(strOptString5) ? strOptString5 : null;
        Float fFloatOrNull7 = floatOrNull(o, "pageWidth");
        Float fFloatOrNull8 = floatOrNull(o, "pageHeight");
        String strOptString6 = o.optString("orientation");
        Intrinsics.checkNotNull(strOptString6);
        String str5 = !StringsKt.isBlank(strOptString6) ? strOptString6 : null;
        Float fFloatOrNull9 = floatOrNull(o, "margin");
        String strOptString7 = o.optString("backgroundColor");
        Intrinsics.checkNotNull(strOptString7);
        return new Command(upperCase, l, str, str2, fFloatOrNull, fFloatOrNull2, fFloatOrNull3, fFloatOrNull4, fFloatOrNull5, fFloatOrNull6, str3, str4, fFloatOrNull7, fFloatOrNull8, str5, fFloatOrNull9, !StringsKt.isBlank(strOptString7) ? strOptString7 : null);
    }

    private final Float floatOrNull(JSONObject jSONObject, String str) {
        if (!jSONObject.has(str) || jSONObject.isNull(str)) {
            return null;
        }
        return Float.valueOf((float) jSONObject.optDouble(str));
    }
}
