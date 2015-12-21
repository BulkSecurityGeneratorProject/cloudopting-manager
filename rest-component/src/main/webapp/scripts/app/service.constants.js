"use strict";
// DO NOT EDIT THIS FILE UNLESS YOU KNOW WHAT ARE YOU MODIFYING.
angular.module('cloudoptingApp')
    .constant("SERVICE", {
        "SEPARATOR" : "/",
        "ROLE" : {
            "PUBLISHER"  : "ROLE_PUBLISHER",
            "ADMIN"  : "ROLE_ADMIN",
            "OPERATOR"  : "ROLE_OPERATOR",
            "SUBSCRIBER"  : "ROLE_SUBSCRIBER"
        },
        "STATUS" : {
            "UNFINISHED" : "UNFINISHED",
            "UPLOADED" : "UPLOADED",
            "DRAFT" : "DRAFT",
            "READY_TO_PUBLISH" : "READY_TO_PUBLISH",
            "PUBLISHED" : "PUBLISHED"
        },
        "STORAGE" : {
            "CURRENT_APP" : "currentApplication",
            "CURRENT_ORGANIZATION" : "currentOrganization",
            "CURRENT_INSTANCE" : "currentInstance",
            "CURRENT_INSTANCE_ID" : "currentInstanceId",
            "ACTIVITI" : "activiti",
            "CURRENT_EDIT_USER" : "currentEditUser",
            "CURRENT_EDIT_ORG" : "currentEditOrganization",
            "PUBLISH_EDITION" : "isApplicationBeingEdited",
            "CURRENT_CLOUDACCOUNT" : "currentCloudAccount",
            "WIZARD_INSTANCES" : {
                "ORGANIZATION" : "storageWizardInstancesOrganization",
                "FUNCTION" : "storageWizardInstancesFunction"
            }
        },
        "FILE_TYPE" : {
            "PROMO_IMAGE" : "promoimage",
            "CONTENT_LIBRARY" : "contentlibrary",
            "TOSCA_ARCHIVE" : "toscaarchive"
        }
    }
)
;