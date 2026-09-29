/*
 * Forked from:
 * https://gitlab.com/ReVanced/revanced-patches/-/blob/main/patches/src/main/kotlin/app/revanced/patches/gmxmail/freephone/Fingerprints.kt
 */
package app.morphe.patches.gmxmail.freephone

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.InstructionLocation.MatchAfterImmediately
import app.morphe.patcher.methodCall
import app.morphe.patcher.opcode
import com.android.tools.smali.dexlib2.Opcode

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
