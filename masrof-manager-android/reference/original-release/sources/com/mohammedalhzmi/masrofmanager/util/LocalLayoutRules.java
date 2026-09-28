package com.mohammedalhzmi.masrofmanager.util;

import com.mohammedalhzmi.masrofmanager.data.DesignElementEntity;
import com.mohammedalhzmi.masrofmanager.data.DocumentDesignEntity;
import io.ktor.sse.ServerSentEventKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: HybridAiAssistant.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J,\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\fJ%\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005H\u0002¢\u0006\u0002\u0010\u000f¨\u0006\u0010"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/util/LocalLayoutRules;", "", "<init>", "()V", "plan", "", "Lcom/mohammedalhzmi/masrofmanager/util/AiLayoutAssistant$Command;", "text", "", "elements", "Lcom/mohammedalhzmi/masrofmanager/data/DesignElementEntity;", "design", "Lcom/mohammedalhzmi/masrofmanager/data/DocumentDesignEntity;", "findTargetId", "", "(Ljava/lang/String;Ljava/util/List;)Ljava/lang/Long;", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final class LocalLayoutRules {
    public static final int $stable = 0;
    public static final LocalLayoutRules INSTANCE = new LocalLayoutRules();

    private LocalLayoutRules() {
    }

    /* JADX WARN: Code duplicated, block: B:127:0x0500  */
    /* JADX WARN: Code duplicated, block: B:135:0x0552  */
    /* JADX WARN: Code duplicated, block: B:137:0x0559  */
    /* JADX WARN: Code duplicated, block: B:140:0x0566  */
    /* JADX WARN: Code duplicated, block: B:146:0x05b5  */
    /* JADX WARN: Code duplicated, block: B:153:0x0624 A[LOOP:3: B:151:0x061e->B:153:0x0624, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:166:0x0272 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:169:0x0258 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:174:0x01cc A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:177:0x01b2 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:30:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:33:0x00b9 A[LOOP:2: B:31:0x00b3->B:33:0x00b9, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:38:0x012b  */
    /* JADX WARN: Code duplicated, block: B:41:0x0138 A[LOOP:8: B:39:0x0132->B:41:0x0138, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:48:0x01a7  */
    /* JADX WARN: Code duplicated, block: B:51:0x01b8  */
    /* JADX WARN: Code duplicated, block: B:60:0x01e6 A[LOOP:7: B:58:0x01e0->B:60:0x01e6, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:65:0x024d  */
    /* JADX WARN: Code duplicated, block: B:68:0x025e  */
    /* JADX WARN: Code duplicated, block: B:77:0x028c A[LOOP:5: B:75:0x0286->B:77:0x028c, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:83:0x0302  */
    /* JADX WARN: Code duplicated, block: B:84:0x0307  */
    /* JADX WARN: Code duplicated, block: B:87:0x0314  */
    /* JADX WARN: Code duplicated, block: B:88:0x0319  */
    /* JADX WARN: Code duplicated, block: B:95:0x0369  */
    /* JADX WARN: Code duplicated, block: B:96:0x036e  */
    public final List<AiLayoutAssistant.Command> plan(String text, List<DesignElementEntity> elements, DocumentDesignEntity design) {
        Object next;
        String str;
        String str2;
        ArrayList<DesignElementEntity> arrayList;
        Long lFindTargetId;
        ArrayList<DesignElementEntity> arrayList2;
        Long lFindTargetId2;
        Iterator it;
        float pageWidth;
        float pageWidth2;
        float pageHeight;
        Iterator it2;
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(elements, "elements");
        String lowerCase = text.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        ArrayList arrayList3 = new ArrayList();
        List<DesignElementEntity> list = elements;
        ArrayList arrayList4 = new ArrayList();
        for (Object obj : list) {
            if (Intrinsics.areEqual(((DesignElementEntity) obj).getType(), "TEXT")) {
                arrayList4.add(obj);
            }
        }
        ArrayList<DesignElementEntity> arrayList5 = arrayList4;
        Iterator<T> it3 = list.iterator();
        do {
            if (!it3.hasNext()) {
                next = null;
                break;
            }
            next = it3.next();
        } while (!Intrinsics.areEqual(((DesignElementEntity) next).getType(), "QR"));
        DesignElementEntity designElementEntity = (DesignElementEntity) next;
        String str3 = lowerCase;
        if (StringsKt.contains$default((CharSequence) str3, (CharSequence) "وسط", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str3, (CharSequence) "منتصف", false, 2, (Object) null)) {
            str = "CENTER";
        } else {
            if (!StringsKt.contains$default((CharSequence) str3, (CharSequence) "يمين", false, 2, (Object) null)) {
                if (StringsKt.contains$default((CharSequence) str3, (CharSequence) "يسار", false, 2, (Object) null)) {
                    str = "END";
                } else {
                    str2 = null;
                }
                if (str2 != null) {
                    it2 = arrayList5.iterator();
                    while (it2.hasNext()) {
                        arrayList3.add(new AiLayoutAssistant.Command("STYLE_TEXT", Long.valueOf(((DesignElementEntity) it2.next()).getId()), null, null, null, null, null, null, null, null, null, str2, null, null, null, null, null, 129020, null));
                    }
                }
                if (!StringsKt.contains$default((CharSequence) str3, (CharSequence) "عريض", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str3, (CharSequence) "غامق", false, 2, (Object) null)) {
                    for (DesignElementEntity designElementEntity2 : arrayList5) {
                        arrayList3.add(new AiLayoutAssistant.Command("STYLE_TEXT", Long.valueOf(designElementEntity2.getId()), null, null, null, null, null, null, null, Float.valueOf(RangesKt.coerceAtMost(designElementEntity2.getFontSize() + 2.0f, 72.0f)), null, null, null, null, null, null, null, 130556, null));
                    }
                }
                if (!StringsKt.contains$default((CharSequence) str3, (CharSequence) "كبّر", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str3, (CharSequence) "تكبير", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str3, (CharSequence) "أكبر", false, 2, (Object) null)) {
                    arrayList = new ArrayList();
                    for (Object obj2 : list) {
                        long id = ((DesignElementEntity) obj2).getId();
                        lFindTargetId = INSTANCE.findTargetId(lowerCase, elements);
                        if (lFindTargetId == null && id == lFindTargetId.longValue()) {
                            arrayList.add(obj2);
                        }
                    }
                    for (DesignElementEntity designElementEntity3 : arrayList) {
                        arrayList3.add(new AiLayoutAssistant.Command("RESIZE", Long.valueOf(designElementEntity3.getId()), null, null, null, null, Float.valueOf(designElementEntity3.getWidth() * 1.2f), Float.valueOf(designElementEntity3.getHeight() * 1.2f), null, null, null, null, null, null, null, null, null, 130876, null));
                    }
                }
                if (!StringsKt.contains$default((CharSequence) str3, (CharSequence) "صغّر", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str3, (CharSequence) "تصغير", false, 2, (Object) null)) {
                    arrayList2 = new ArrayList();
                    for (Object obj3 : list) {
                        long id2 = ((DesignElementEntity) obj3).getId();
                        lFindTargetId2 = INSTANCE.findTargetId(lowerCase, elements);
                        if (lFindTargetId2 == null && id2 == lFindTargetId2.longValue()) {
                            arrayList2.add(obj3);
                        }
                    }
                    for (DesignElementEntity designElementEntity4 : arrayList2) {
                        arrayList3.add(new AiLayoutAssistant.Command("RESIZE", Long.valueOf(designElementEntity4.getId()), null, null, null, null, Float.valueOf(designElementEntity4.getWidth() * 0.8f), Float.valueOf(designElementEntity4.getHeight() * 0.8f), null, null, null, null, null, null, null, null, null, 130876, null));
                    }
                }
                if (StringsKt.contains$default((CharSequence) str3, (CharSequence) "أسفل", false, 2, (Object) null) && designElementEntity != null) {
                    ArrayList arrayList6 = arrayList3;
                    Long lValueOf = Long.valueOf(designElementEntity.getId());
                    if (design != null) {
                        pageWidth2 = design.getPageWidth();
                    } else {
                        pageWidth2 = 595.0f;
                    }
                    Float fValueOf = Float.valueOf((pageWidth2 - designElementEntity.getWidth()) - 35.0f);
                    if (design != null) {
                        pageHeight = design.getPageHeight();
                    } else {
                        pageHeight = 842.0f;
                    }
                    arrayList6.add(new AiLayoutAssistant.Command("MOVE", lValueOf, null, null, fValueOf, Float.valueOf((pageHeight - designElementEntity.getHeight()) - 35.0f), null, null, null, null, null, null, null, null, null, null, null, 131020, null));
                }
                if (StringsKt.contains$default((CharSequence) str3, (CharSequence) "أعلى", false, 2, (Object) null) && designElementEntity != null) {
                    ArrayList arrayList7 = arrayList3;
                    Long lValueOf2 = Long.valueOf(designElementEntity.getId());
                    if (design != null) {
                        pageWidth = design.getPageWidth();
                    } else {
                        pageWidth = 595.0f;
                    }
                    arrayList7.add(new AiLayoutAssistant.Command("MOVE", lValueOf2, null, null, Float.valueOf((pageWidth - designElementEntity.getWidth()) - 35.0f), Float.valueOf(35.0f), null, null, null, null, null, null, null, null, null, null, null, 131020, null));
                }
                if ((StringsKt.contains$default((CharSequence) str3, (CharSequence) "أفقي", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str3, (CharSequence) "عرضي", false, 2, (Object) null)) && design != null) {
                    arrayList3.add(new AiLayoutAssistant.Command("PAGE", null, null, null, null, null, null, null, null, null, null, null, Float.valueOf(842.0f), Float.valueOf(595.0f), "LANDSCAPE", null, null, 102398, null));
                }
                if ((StringsKt.contains$default((CharSequence) str3, (CharSequence) "عمودي", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str3, (CharSequence) "طولي", false, 2, (Object) null)) && design != null) {
                    arrayList3.add(new AiLayoutAssistant.Command("PAGE", null, null, null, null, null, null, null, null, null, null, null, Float.valueOf(595.0f), Float.valueOf(842.0f), "PORTRAIT", null, null, 102398, null));
                }
                if (StringsKt.contains$default((CharSequence) str3, (CharSequence) "a5", false, 2, (Object) null) && design != null) {
                    arrayList3.add(new AiLayoutAssistant.Command("PAGE", null, null, null, null, null, null, null, null, null, null, null, Float.valueOf(420.0f), Float.valueOf(595.0f), "PORTRAIT", null, null, 102398, null));
                }
                if ((StringsKt.contains$default((CharSequence) str3, (CharSequence) "a4", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str3, (CharSequence) "ورقة كاملة", false, 2, (Object) null)) && design != null) {
                    arrayList3.add(new AiLayoutAssistant.Command("PAGE", null, null, null, null, null, null, null, null, null, null, null, Float.valueOf(595.0f), Float.valueOf(842.0f), "PORTRAIT", null, null, 102398, null));
                }
                if (StringsKt.contains$default((CharSequence) str3, (CharSequence) "هوامش", false, 2, (Object) null) && design != null) {
                    arrayList3.add(new AiLayoutAssistant.Command("PAGE", null, null, null, null, null, null, null, null, null, null, null, null, null, null, Float.valueOf((!StringsKt.contains$default((CharSequence) str3, (CharSequence) "ضيقة", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str3, (CharSequence) "صغيرة", false, 2, (Object) null)) ? 12.0f : 35.0f), null, 98302, null));
                }
                if (StringsKt.contains$default((CharSequence) str3, (CharSequence) "أضف qr", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str3, (CharSequence) "اضف qr", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str3, (CharSequence) "رمز qr", false, 2, (Object) null)) {
                    arrayList3.add(new AiLayoutAssistant.Command("ADD_QR", null, "QR", "{رقم المستند}", Float.valueOf((design != null ? design.getPageWidth() : 595.0f) - 115.0f), Float.valueOf((design != null ? design.getPageHeight() : 842.0f) - 115.0f), Float.valueOf(80.0f), Float.valueOf(80.0f), null, null, null, null, null, null, null, null, null, 130818, null));
                }
                if (StringsKt.contains$default((CharSequence) str3, (CharSequence) "أضف نص", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str3, (CharSequence) "اضف نص", false, 2, (Object) null)) {
                    arrayList3.add(new AiLayoutAssistant.Command("ADD_TEXT", null, "TEXT", StringsKt.trim((CharSequence) StringsKt.substringAfter(text, ServerSentEventKt.COLON, "نص جديد")).toString(), Float.valueOf(40.0f), Float.valueOf(140.0f), Float.valueOf(300.0f), Float.valueOf(70.0f), null, null, null, null, null, null, null, null, null, 130818, null));
                }
                if (arrayList3.isEmpty() && str2 == null) {
                    it = CollectionsKt.take(arrayList5, 1).iterator();
                    while (it.hasNext()) {
                        arrayList3.add(new AiLayoutAssistant.Command("STYLE_TEXT", Long.valueOf(((DesignElementEntity) it.next()).getId()), null, null, null, null, null, null, null, null, null, "CENTER", null, null, null, null, null, 129020, null));
                    }
                }
                return arrayList3;
            }
            str = "START";
        }
        str2 = str;
        if (str2 != null) {
            it2 = arrayList5.iterator();
            while (it2.hasNext()) {
                arrayList3.add(new AiLayoutAssistant.Command("STYLE_TEXT", Long.valueOf(((DesignElementEntity) it2.next()).getId()), null, null, null, null, null, null, null, null, null, str2, null, null, null, null, null, 129020, null));
            }
        }
        if (!StringsKt.contains$default((CharSequence) str3, (CharSequence) "عريض", false, 2, (Object) null)) {
            while (r8.hasNext()) {
                arrayList3.add(new AiLayoutAssistant.Command("STYLE_TEXT", Long.valueOf(designElementEntity2.getId()), null, null, null, null, null, null, null, Float.valueOf(RangesKt.coerceAtMost(designElementEntity2.getFontSize() + 2.0f, 72.0f)), null, null, null, null, null, null, null, 130556, null));
            }
        } else {
            while (r8.hasNext()) {
                arrayList3.add(new AiLayoutAssistant.Command("STYLE_TEXT", Long.valueOf(designElementEntity2.getId()), null, null, null, null, null, null, null, Float.valueOf(RangesKt.coerceAtMost(designElementEntity2.getFontSize() + 2.0f, 72.0f)), null, null, null, null, null, null, null, 130556, null));
            }
        }
        if (!StringsKt.contains$default((CharSequence) str3, (CharSequence) "كبّر", false, 2, (Object) null)) {
            arrayList = new ArrayList();
            while (r9.hasNext()) {
                long id3 = ((DesignElementEntity) obj2).getId();
                lFindTargetId = INSTANCE.findTargetId(lowerCase, elements);
                if (lFindTargetId == null) {
                    arrayList.add(obj2);
                }
            }
            while (r8.hasNext()) {
                arrayList3.add(new AiLayoutAssistant.Command("RESIZE", Long.valueOf(designElementEntity3.getId()), null, null, null, null, Float.valueOf(designElementEntity3.getWidth() * 1.2f), Float.valueOf(designElementEntity3.getHeight() * 1.2f), null, null, null, null, null, null, null, null, null, 130876, null));
            }
        } else {
            arrayList = new ArrayList();
            while (r9.hasNext()) {
                long id4 = ((DesignElementEntity) obj2).getId();
                lFindTargetId = INSTANCE.findTargetId(lowerCase, elements);
                if (lFindTargetId == null) {
                    arrayList.add(obj2);
                }
            }
            while (r8.hasNext()) {
                arrayList3.add(new AiLayoutAssistant.Command("RESIZE", Long.valueOf(designElementEntity3.getId()), null, null, null, null, Float.valueOf(designElementEntity3.getWidth() * 1.2f), Float.valueOf(designElementEntity3.getHeight() * 1.2f), null, null, null, null, null, null, null, null, null, 130876, null));
            }
        }
        if (!StringsKt.contains$default((CharSequence) str3, (CharSequence) "صغّر", false, 2, (Object) null)) {
            arrayList2 = new ArrayList();
            while (r4.hasNext()) {
                long id5 = ((DesignElementEntity) obj3).getId();
                lFindTargetId2 = INSTANCE.findTargetId(lowerCase, elements);
                if (lFindTargetId2 == null) {
                    arrayList2.add(obj3);
                }
            }
            while (r1.hasNext()) {
                arrayList3.add(new AiLayoutAssistant.Command("RESIZE", Long.valueOf(designElementEntity4.getId()), null, null, null, null, Float.valueOf(designElementEntity4.getWidth() * 0.8f), Float.valueOf(designElementEntity4.getHeight() * 0.8f), null, null, null, null, null, null, null, null, null, 130876, null));
            }
        } else {
            arrayList2 = new ArrayList();
            while (r4.hasNext()) {
                long id6 = ((DesignElementEntity) obj3).getId();
                lFindTargetId2 = INSTANCE.findTargetId(lowerCase, elements);
                if (lFindTargetId2 == null) {
                    arrayList2.add(obj3);
                }
            }
            while (r1.hasNext()) {
                arrayList3.add(new AiLayoutAssistant.Command("RESIZE", Long.valueOf(designElementEntity4.getId()), null, null, null, null, Float.valueOf(designElementEntity4.getWidth() * 0.8f), Float.valueOf(designElementEntity4.getHeight() * 0.8f), null, null, null, null, null, null, null, null, null, 130876, null));
            }
        }
        if (StringsKt.contains$default((CharSequence) str3, (CharSequence) "أسفل", false, 2, (Object) null)) {
            ArrayList arrayList8 = arrayList3;
            Long lValueOf3 = Long.valueOf(designElementEntity.getId());
            if (design != null) {
                pageWidth2 = design.getPageWidth();
            } else {
                pageWidth2 = 595.0f;
            }
            Float fValueOf2 = Float.valueOf((pageWidth2 - designElementEntity.getWidth()) - 35.0f);
            if (design != null) {
                pageHeight = design.getPageHeight();
            } else {
                pageHeight = 842.0f;
            }
            arrayList8.add(new AiLayoutAssistant.Command("MOVE", lValueOf3, null, null, fValueOf2, Float.valueOf((pageHeight - designElementEntity.getHeight()) - 35.0f), null, null, null, null, null, null, null, null, null, null, null, 131020, null));
        }
        if (StringsKt.contains$default((CharSequence) str3, (CharSequence) "أعلى", false, 2, (Object) null)) {
            ArrayList arrayList9 = arrayList3;
            Long lValueOf4 = Long.valueOf(designElementEntity.getId());
            if (design != null) {
                pageWidth = design.getPageWidth();
            } else {
                pageWidth = 595.0f;
            }
            arrayList9.add(new AiLayoutAssistant.Command("MOVE", lValueOf4, null, null, Float.valueOf((pageWidth - designElementEntity.getWidth()) - 35.0f), Float.valueOf(35.0f), null, null, null, null, null, null, null, null, null, null, null, 131020, null));
        }
        if (StringsKt.contains$default((CharSequence) str3, (CharSequence) "أفقي", false, 2, (Object) null)) {
            arrayList3.add(new AiLayoutAssistant.Command("PAGE", null, null, null, null, null, null, null, null, null, null, null, Float.valueOf(842.0f), Float.valueOf(595.0f), "LANDSCAPE", null, null, 102398, null));
        } else {
            arrayList3.add(new AiLayoutAssistant.Command("PAGE", null, null, null, null, null, null, null, null, null, null, null, Float.valueOf(842.0f), Float.valueOf(595.0f), "LANDSCAPE", null, null, 102398, null));
        }
        if (StringsKt.contains$default((CharSequence) str3, (CharSequence) "عمودي", false, 2, (Object) null)) {
            arrayList3.add(new AiLayoutAssistant.Command("PAGE", null, null, null, null, null, null, null, null, null, null, null, Float.valueOf(595.0f), Float.valueOf(842.0f), "PORTRAIT", null, null, 102398, null));
        } else {
            arrayList3.add(new AiLayoutAssistant.Command("PAGE", null, null, null, null, null, null, null, null, null, null, null, Float.valueOf(595.0f), Float.valueOf(842.0f), "PORTRAIT", null, null, 102398, null));
        }
        if (StringsKt.contains$default((CharSequence) str3, (CharSequence) "a5", false, 2, (Object) null)) {
            arrayList3.add(new AiLayoutAssistant.Command("PAGE", null, null, null, null, null, null, null, null, null, null, null, Float.valueOf(420.0f), Float.valueOf(595.0f), "PORTRAIT", null, null, 102398, null));
        }
        if (StringsKt.contains$default((CharSequence) str3, (CharSequence) "a4", false, 2, (Object) null)) {
            arrayList3.add(new AiLayoutAssistant.Command("PAGE", null, null, null, null, null, null, null, null, null, null, null, Float.valueOf(595.0f), Float.valueOf(842.0f), "PORTRAIT", null, null, 102398, null));
        } else {
            arrayList3.add(new AiLayoutAssistant.Command("PAGE", null, null, null, null, null, null, null, null, null, null, null, Float.valueOf(595.0f), Float.valueOf(842.0f), "PORTRAIT", null, null, 102398, null));
        }
        if (StringsKt.contains$default((CharSequence) str3, (CharSequence) "هوامش", false, 2, (Object) null)) {
            arrayList3.add(new AiLayoutAssistant.Command("PAGE", null, null, null, null, null, null, null, null, null, null, null, null, null, null, Float.valueOf((!StringsKt.contains$default((CharSequence) str3, (CharSequence) "ضيقة", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str3, (CharSequence) "صغيرة", false, 2, (Object) null)) ? 12.0f : 35.0f), null, 98302, null));
        }
        if (StringsKt.contains$default((CharSequence) str3, (CharSequence) "أضف qr", false, 2, (Object) null)) {
            arrayList3.add(new AiLayoutAssistant.Command("ADD_QR", null, "QR", "{رقم المستند}", Float.valueOf((design != null ? design.getPageWidth() : 595.0f) - 115.0f), Float.valueOf((design != null ? design.getPageHeight() : 842.0f) - 115.0f), Float.valueOf(80.0f), Float.valueOf(80.0f), null, null, null, null, null, null, null, null, null, 130818, null));
        } else {
            arrayList3.add(new AiLayoutAssistant.Command("ADD_QR", null, "QR", "{رقم المستند}", Float.valueOf((design != null ? design.getPageWidth() : 595.0f) - 115.0f), Float.valueOf((design != null ? design.getPageHeight() : 842.0f) - 115.0f), Float.valueOf(80.0f), Float.valueOf(80.0f), null, null, null, null, null, null, null, null, null, 130818, null));
        }
        if (StringsKt.contains$default((CharSequence) str3, (CharSequence) "أضف نص", false, 2, (Object) null)) {
            arrayList3.add(new AiLayoutAssistant.Command("ADD_TEXT", null, "TEXT", StringsKt.trim((CharSequence) StringsKt.substringAfter(text, ServerSentEventKt.COLON, "نص جديد")).toString(), Float.valueOf(40.0f), Float.valueOf(140.0f), Float.valueOf(300.0f), Float.valueOf(70.0f), null, null, null, null, null, null, null, null, null, 130818, null));
        } else {
            arrayList3.add(new AiLayoutAssistant.Command("ADD_TEXT", null, "TEXT", StringsKt.trim((CharSequence) StringsKt.substringAfter(text, ServerSentEventKt.COLON, "نص جديد")).toString(), Float.valueOf(40.0f), Float.valueOf(140.0f), Float.valueOf(300.0f), Float.valueOf(70.0f), null, null, null, null, null, null, null, null, null, 130818, null));
        }
        if (arrayList3.isEmpty()) {
            it = CollectionsKt.take(arrayList5, 1).iterator();
            while (it.hasNext()) {
                arrayList3.add(new AiLayoutAssistant.Command("STYLE_TEXT", Long.valueOf(((DesignElementEntity) it.next()).getId()), null, null, null, null, null, null, null, null, null, "CENTER", null, null, null, null, null, 129020, null));
            }
        }
        return arrayList3;
    }

    private final Long findTargetId(String text, List<DesignElementEntity> elements) {
        Object next;
        Object next2;
        long id;
        List<DesignElementEntity> list = elements;
        Iterator<T> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (StringsKt.contains$default((CharSequence) ((DesignElementEntity) next).getContent(), (CharSequence) "عنوان", false, 2, (Object) null) && StringsKt.contains$default((CharSequence) text, (CharSequence) "عنوان", false, 2, (Object) null)) {
                break;
            }
        }
        DesignElementEntity designElementEntity = (DesignElementEntity) next;
        if (designElementEntity != null) {
            id = designElementEntity.getId();
        } else {
            Iterator<T> it2 = list.iterator();
            do {
                if (!it2.hasNext()) {
                    next2 = null;
                    break;
                }
                next2 = it2.next();
            } while (!Intrinsics.areEqual(((DesignElementEntity) next2).getType(), "TEXT"));
            DesignElementEntity designElementEntity2 = (DesignElementEntity) next2;
            if (designElementEntity2 == null) {
                return null;
            }
            id = designElementEntity2.getId();
        }
        return Long.valueOf(id);
    }
}
