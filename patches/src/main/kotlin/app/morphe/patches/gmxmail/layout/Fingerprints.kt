/*
 * Forked from:
 * https://gitlab.com/ReVanced/revanced-patches/-/blob/main/patches/src/main/kotlin/app/revanced/patches/gmxmail/layout/Fingerprints.kt
 */
package app.morphe.patches.gmxmail.layout

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.InstructionLocation.MatchAfterImmediately
import app.morphe.patcher.methodCall
import app.morphe.patcher.opcode
import com.android.tools.smali.dexlib2.Opcode

internal object IsUpsellingPossibleFingerprint : Fingerprint(
    custom = { method, classDef ->
        method.name == "isUpsellingPossible" && classDef.endsWith("/PayMailManager;")
    },
)

internal object NavigationDrawerDisplayFingerprint : Fingerprint(
    definingClass = "Lcom/unitedinternet/portal/navigationDrawer/ui/NavigationDrawerComposableKt;",
    name = "NavigationDrawerUI",
    filters = listOf(
        methodCall(
            definingClass = "Lcom/unitedinternet/portal/android/inapppurchase/entrypoint/model/EntryPointInfo;",
            name = "getDisplay",
            returnType = "Z",
        ),
        opcode(Opcode.MOVE_RESULT, MatchAfterImmediately()),
        methodCall(
            definingClass = "Lcom/unitedinternet/portal/android/inapppurchase/entrypoint/model/EntryPointInfo;",
            name = "getDisplay",
            returnType = "Z",
        ),
        opcode(Opcode.MOVE_RESULT, MatchAfterImmediately()),
    ),
)