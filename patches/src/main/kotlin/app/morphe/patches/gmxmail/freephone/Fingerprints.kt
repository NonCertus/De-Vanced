/*
 * Forked from:
 * https://gitlab.com/ReVanced/revanced-patches/-/blob/main/patches/src/main/kotlin/app/revanced/patches/gmxmail/freephone/Fingerprints.kt
 */
package app.morphe.patches.gmxmail.freephone

import app.morphe.patcher.Fingerprint import app.morphe.patcher.methodCall import app.morphe.patcher.opcode import com.android.tools.smali.dexlib2.Opcode

internal object IsEuiccEnabledFingerprint : Fingerprint(
    custom = { method, _ -> method.name == "isEuiccEnabled" },
)

internal object IsFeatureEnabledFingerprint : Fingerprint(
    definingClass = "Lcom/unitedinternet/portal/android/mail/esim/ESimRepositoryImpl;",
    name = "isFeatureEnabled",
)

internal object HasEsimSupportFingerprint : Fingerprint(
    custom = { method, _ -> method.name == "hasEsimSupport" },
)

internal object ESimTileVisibilityChangedFingerprint : Fingerprint(
    custom = { method, _ -> method.name == "eSimTileVisibilityChanged" },
    filters = listOf(
        opcode(Opcode.MOVE),
    ),
)

internal object EsimEligibilityResultFingerprint : Fingerprint(
    definingClass = "Lcom/unitedinternet/portal/navigationDrawer/viewmodel/NavigationDrawerViewModelImpl\$special\$\$inlined\$map\$1\$2;",
    name = "emit",
    filters = listOf(
        opcode(Opcode.CHECK_CAST),
        methodCall(
            definingClass = "Ljava/lang/Boolean;",
            name = "booleanValue",
            returnType = "Z",
        ),
        opcode(Opcode.MOVE_RESULT),
    ),
)

