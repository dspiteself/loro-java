package com.loro;


import com.sun.jna.Library;
import com.sun.jna.Native;

final class NamespaceLibrary {
  static synchronized String findLibraryName(String componentName) {
    String libOverride = System.getProperty("uniffi.component." + componentName + ".libraryOverride");
    if (libOverride != null) {
        return libOverride;
    }
    return "uniffi_loro";
  }

  static <Lib extends Library> Lib loadIndirect(String componentName, Class<Lib> clazz) {
    return Native.load(findLibraryName(componentName), clazz);
  }

  static void uniffiCheckContractApiVersion(UniffiLib lib) {
    // Get the bindings contract version from our ComponentInterface
    int bindingsContractVersion = 26;
    // Get the scaffolding contract version by calling the into the dylib
    int scaffoldingContractVersion = lib.ffi_loro_uniffi_contract_version();
    if (bindingsContractVersion != scaffoldingContractVersion) {
        throw new RuntimeException("UniFFI contract version mismatch: try cleaning and rebuilding your project");
    }
  }

  static void uniffiCheckApiChecksums(UniffiLib lib) {
    if (lib.uniffi_loro_checksum_func_decode_import_blob_meta() != ((short) 2769)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_func_get_version() != ((short) 3250)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_awareness_apply() != ((short) 41900)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_awareness_encode() != ((short) 37443)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_awareness_encode_all() != ((short) 38982)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_awareness_get_all_states() != ((short) 30017)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_awareness_get_local_state() != ((short) 59706)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_awareness_peer() != ((short) 10202)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_awareness_remove_outdated() != ((short) 1483)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_awareness_set_local_state() != ((short) 517)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_changeancestorstraveler_travel() != ((short) 17239)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_configure_fork() != ((short) 57176)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_configure_merge_interval() != ((short) 43546)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_configure_record_timestamp() != ((short) 41033)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_configure_set_merge_interval() != ((short) 4893)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_configure_set_record_timestamp() != ((short) 30145)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_configure_text_style_config() != ((short) 50151)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_containeridlike_as_container_id() != ((short) 41081)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_diffbatch_get_diff() != ((short) 42707)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_diffbatch_push() != ((short) 56678)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_fractionalindex_to_string() != ((short) 57024)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_frontiers_encode() != ((short) 48230)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_frontiers_eq() != ((short) 20207)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_localupdatecallback_on_local_update() != ((short) 21789)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorocounter_decrement() != ((short) 53919)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorocounter_doc() != ((short) 54846)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorocounter_get_attached() != ((short) 22021)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorocounter_get_value() != ((short) 44616)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorocounter_id() != ((short) 31148)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorocounter_increment() != ((short) 47367)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorocounter_is_attached() != ((short) 51768)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorocounter_is_deleted() != ((short) 12079)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_apply_diff() != ((short) 45393)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_attach() != ((short) 7252)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_check_state_correctness_slow() != ((short) 43878)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_checkout() != ((short) 415)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_checkout_to_latest() != ((short) 2349)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_clear_next_commit_options() != ((short) 45217)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_cmp_with_frontiers() != ((short) 31942)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_commit() != ((short) 53174)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_commit_with() != ((short) 29999)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_compact_change_store() != ((short) 26224)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_config() != ((short) 3400)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_config_default_text_style() != ((short) 15083)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_config_text_style() != ((short) 52393)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_detach() != ((short) 61399)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_diff() != ((short) 38416)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_export_json_in_id_span() != ((short) 26608)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_export_json_updates() != ((short) 15152)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_export_json_updates_without_peer_compression() != ((short) 23184)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_export_shallow_snapshot() != ((short) 27927)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_export_snapshot() != ((short) 61274)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_export_snapshot_at() != ((short) 64602)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_export_state_only() != ((short) 16747)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_export_updates() != ((short) 57637)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_export_updates_in_range() != ((short) 22491)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_find_id_spans_between() != ((short) 1313)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_fork() != ((short) 45665)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_fork_at() != ((short) 40377)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_free_diff_calculator() != ((short) 32937)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_free_history_cache() != ((short) 22144)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_frontiers_to_vv() != ((short) 11123)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_get_by_path() != ((short) 35945)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_get_by_str_path() != ((short) 6739)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_get_change() != ((short) 17896)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_get_changed_containers_in() != ((short) 52454)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_get_counter() != ((short) 12597)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_get_cursor_pos() != ((short) 30480)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_get_deep_value() != ((short) 3404)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_get_deep_value_with_id() != ((short) 49124)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_get_list() != ((short) 9609)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_get_map() != ((short) 63137)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_get_movable_list() != ((short) 7302)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_get_path_to_container() != ((short) 62623)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_get_pending_txn_len() != ((short) 15050)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_get_text() != ((short) 56069)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_get_tree() != ((short) 54189)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_get_value() != ((short) 29857)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_has_container() != ((short) 41856)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_has_history_cache() != ((short) 53741)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_import() != ((short) 11528)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_import_batch() != ((short) 34010)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_import_json_updates() != ((short) 57379)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_import_with() != ((short) 12897)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_is_detached() != ((short) 30909)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_is_shallow() != ((short) 53044)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_jsonpath() != ((short) 15996)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_len_changes() != ((short) 62401)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_len_ops() != ((short) 11644)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_log_estimate_size() != ((short) 19429)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_minimize_frontiers() != ((short) 39579)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_oplog_frontiers() != ((short) 49043)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_oplog_vv() != ((short) 56754)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_peer_id() != ((short) 35449)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_revert_to() != ((short) 48346)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_set_change_merge_interval() != ((short) 55133)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_set_next_commit_message() != ((short) 18940)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_set_next_commit_options() != ((short) 13250)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_set_next_commit_origin() != ((short) 27549)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_set_next_commit_timestamp() != ((short) 30492)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_set_peer_id() != ((short) 29379)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_set_record_timestamp() != ((short) 15945)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_shallow_since_vv() != ((short) 13449)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_state_frontiers() != ((short) 17079)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_state_vv() != ((short) 1627)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_subscribe() != ((short) 7981)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_subscribe_local_update() != ((short) 58652)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_subscribe_root() != ((short) 16564)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_travel_change_ancestors() != ((short) 39918)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorodoc_vv_to_frontiers() != ((short) 47960)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorolist_clear() != ((short) 61243)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorolist_delete() != ((short) 40414)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorolist_doc() != ((short) 4182)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorolist_get() != ((short) 36174)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorolist_get_attached() != ((short) 5208)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorolist_get_cursor() != ((short) 42636)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorolist_get_deep_value() != ((short) 9355)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorolist_get_id_at() != ((short) 63640)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorolist_get_value() != ((short) 14384)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorolist_id() != ((short) 33887)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorolist_insert() != ((short) 19544)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorolist_insert_counter_container() != ((short) 41569)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorolist_insert_list_container() != ((short) 42165)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorolist_insert_map_container() != ((short) 25622)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorolist_insert_movable_list_container() != ((short) 23559)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorolist_insert_text_container() != ((short) 26631)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorolist_insert_tree_container() != ((short) 15665)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorolist_is_attached() != ((short) 60548)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorolist_is_deleted() != ((short) 44383)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorolist_is_empty() != ((short) 13469)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorolist_len() != ((short) 22800)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorolist_pop() != ((short) 20748)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorolist_push() != ((short) 32091)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorolist_to_vec() != ((short) 34199)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromap_clear() != ((short) 22445)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromap_delete() != ((short) 54675)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromap_doc() != ((short) 4684)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromap_get() != ((short) 57695)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromap_get_attached() != ((short) 22266)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromap_get_deep_value() != ((short) 23748)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromap_get_last_editor() != ((short) 54864)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromap_get_or_create_counter_container() != ((short) 34280)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromap_get_or_create_list_container() != ((short) 51559)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromap_get_or_create_map_container() != ((short) 8592)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromap_get_or_create_movable_list_container() != ((short) 15746)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromap_get_or_create_text_container() != ((short) 13374)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromap_get_or_create_tree_container() != ((short) 4760)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromap_get_value() != ((short) 7268)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromap_id() != ((short) 65486)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromap_insert() != ((short) 748)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromap_insert_counter_container() != ((short) 5567)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromap_insert_list_container() != ((short) 52804)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromap_insert_map_container() != ((short) 36523)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromap_insert_movable_list_container() != ((short) 21076)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromap_insert_text_container() != ((short) 50348)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromap_insert_tree_container() != ((short) 59771)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromap_is_attached() != ((short) 6283)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromap_is_deleted() != ((short) 20980)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromap_is_empty() != ((short) 38768)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromap_keys() != ((short) 11621)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromap_len() != ((short) 38088)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromap_values() != ((short) 46629)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromovablelist_clear() != ((short) 17252)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromovablelist_delete() != ((short) 51786)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromovablelist_doc() != ((short) 13729)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromovablelist_get() != ((short) 10599)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromovablelist_get_attached() != ((short) 53503)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromovablelist_get_creator_at() != ((short) 21542)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromovablelist_get_cursor() != ((short) 118)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromovablelist_get_deep_value() != ((short) 18542)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromovablelist_get_last_editor_at() != ((short) 8998)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromovablelist_get_last_mover_at() != ((short) 26603)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromovablelist_get_value() != ((short) 50843)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromovablelist_id() != ((short) 9803)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromovablelist_insert() != ((short) 28537)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromovablelist_insert_counter_container() != ((short) 56222)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromovablelist_insert_list_container() != ((short) 47190)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromovablelist_insert_map_container() != ((short) 57810)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromovablelist_insert_movable_list_container() != ((short) 6019)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromovablelist_insert_text_container() != ((short) 48945)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromovablelist_insert_tree_container() != ((short) 33670)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromovablelist_is_attached() != ((short) 50724)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromovablelist_is_deleted() != ((short) 37438)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromovablelist_is_empty() != ((short) 44651)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromovablelist_len() != ((short) 28945)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromovablelist_mov() != ((short) 8301)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromovablelist_pop() != ((short) 52086)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromovablelist_push() != ((short) 2828)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromovablelist_set() != ((short) 27054)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromovablelist_set_counter_container() != ((short) 1414)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromovablelist_set_list_container() != ((short) 20393)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromovablelist_set_map_container() != ((short) 20297)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromovablelist_set_movable_list_container() != ((short) 52254)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromovablelist_set_text_container() != ((short) 31935)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromovablelist_set_tree_container() != ((short) 8298)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_loromovablelist_to_vec() != ((short) 28826)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotext_apply_delta() != ((short) 32084)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotext_delete() != ((short) 47933)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotext_delete_utf8() != ((short) 44384)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotext_doc() != ((short) 41195)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotext_get_attached() != ((short) 16919)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotext_get_cursor() != ((short) 57876)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotext_get_editor_at_unicode_pos() != ((short) 24596)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotext_get_richtext_value() != ((short) 45999)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotext_id() != ((short) 30925)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotext_insert() != ((short) 10847)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotext_insert_utf8() != ((short) 8484)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotext_is_attached() != ((short) 45378)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotext_is_deleted() != ((short) 31871)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotext_is_empty() != ((short) 7961)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotext_len_unicode() != ((short) 46650)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotext_len_utf16() != ((short) 18865)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotext_len_utf8() != ((short) 29025)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotext_mark() != ((short) 42690)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotext_push_str() != ((short) 2374)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotext_slice() != ((short) 43152)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotext_splice() != ((short) 30467)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotext_to_delta() != ((short) 15868)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotext_to_string() != ((short) 63765)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotext_unmark() != ((short) 14351)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotext_update() != ((short) 16538)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotext_update_by_line() != ((short) 29301)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotree_children() != ((short) 4750)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotree_children_num() != ((short) 50997)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotree_contains() != ((short) 62174)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotree_create() != ((short) 55490)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotree_create_at() != ((short) 64751)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotree_delete() != ((short) 36355)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotree_disable_fractional_index() != ((short) 52853)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotree_doc() != ((short) 23287)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotree_enable_fractional_index() != ((short) 39633)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotree_fractional_index() != ((short) 51036)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotree_get_attached() != ((short) 57142)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotree_get_last_move_id() != ((short) 12557)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotree_get_meta() != ((short) 3068)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotree_get_value() != ((short) 44704)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotree_get_value_with_meta() != ((short) 7497)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotree_id() != ((short) 4862)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotree_is_attached() != ((short) 37303)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotree_is_deleted() != ((short) 8644)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotree_is_fractional_index_enabled() != ((short) 19364)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotree_is_node_deleted() != ((short) 7339)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotree_mov() != ((short) 33288)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotree_mov_after() != ((short) 48871)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotree_mov_before() != ((short) 39654)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotree_mov_to() != ((short) 21629)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotree_nodes() != ((short) 31738)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotree_parent() != ((short) 6903)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorotree_roots() != ((short) 60881)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorounknown_id() != ((short) 65156)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_lorovaluelike_as_loro_value() != ((short) 23668)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_onpop_on_pop() != ((short) 39438)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_onpush_on_push() != ((short) 46111)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_styleconfigmap_get() != ((short) 25442)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_styleconfigmap_insert() != ((short) 49128)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_subscriber_on_diff() != ((short) 462)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_subscription_detach() != ((short) 64699)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_subscription_unsubscribe() != ((short) 24473)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_undomanager_add_exclude_origin_prefix() != ((short) 61306)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_undomanager_can_redo() != ((short) 61543)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_undomanager_can_undo() != ((short) 51532)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_undomanager_record_new_checkpoint() != ((short) 28438)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_undomanager_redo() != ((short) 54874)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_undomanager_set_max_undo_steps() != ((short) 43243)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_undomanager_set_merge_interval() != ((short) 13688)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_undomanager_set_on_pop() != ((short) 4141)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_undomanager_set_on_push() != ((short) 31009)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_undomanager_undo() != ((short) 14430)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_unsubscriber_on_unsubscribe() != ((short) 17877)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_valueorcontainer_as_container() != ((short) 61163)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_valueorcontainer_as_loro_counter() != ((short) 51072)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_valueorcontainer_as_loro_list() != ((short) 16144)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_valueorcontainer_as_loro_map() != ((short) 62391)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_valueorcontainer_as_loro_movable_list() != ((short) 49359)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_valueorcontainer_as_loro_text() != ((short) 8015)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_valueorcontainer_as_loro_tree() != ((short) 39545)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_valueorcontainer_as_loro_unknown() != ((short) 9911)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_valueorcontainer_as_value() != ((short) 9638)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_valueorcontainer_container_type() != ((short) 56498)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_valueorcontainer_is_container() != ((short) 16329)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_valueorcontainer_is_value() != ((short) 13911)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_versionvector_diff() != ((short) 29529)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_versionvector_encode() != ((short) 64665)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_versionvector_eq() != ((short) 30406)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_versionvector_extend_to_include_vv() != ((short) 28076)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_versionvector_get_last() != ((short) 45434)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_versionvector_get_missing_span() != ((short) 2797)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_versionvector_includes_id() != ((short) 50408)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_versionvector_includes_vv() != ((short) 23089)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_versionvector_intersect_span() != ((short) 54748)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_versionvector_merge() != ((short) 23694)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_versionvector_partial_cmp() != ((short) 27570)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_versionvector_set_end() != ((short) 27313)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_method_versionvector_set_last() != ((short) 40968)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_constructor_awareness_new() != ((short) 33037)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_constructor_cursor_new() != ((short) 11721)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_constructor_diffbatch_new() != ((short) 62583)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_constructor_fractionalindex_from_bytes() != ((short) 35415)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_constructor_fractionalindex_from_hex_string() != ((short) 10737)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_constructor_frontiers_decode() != ((short) 27043)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_constructor_frontiers_from_id() != ((short) 60928)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_constructor_frontiers_from_ids() != ((short) 61832)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_constructor_frontiers_new() != ((short) 5172)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_constructor_lorocounter_new() != ((short) 44867)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_constructor_lorodoc_new() != ((short) 54129)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_constructor_lorolist_new() != ((short) 2861)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_constructor_loromap_new() != ((short) 63806)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_constructor_loromovablelist_new() != ((short) 32944)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_constructor_lorotext_new() != ((short) 33191)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_constructor_lorotree_new() != ((short) 42150)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_constructor_styleconfigmap_default_rich_text_config() != ((short) 46944)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_constructor_styleconfigmap_new() != ((short) 23831)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_constructor_undomanager_new() != ((short) 35328)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_constructor_versionvector_decode() != ((short) 19639)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
    if (lib.uniffi_loro_checksum_constructor_versionvector_new() != ((short) 31126)) {
        throw new RuntimeException("UniFFI API checksum mismatch: try cleaning and rebuilding your project");
    }
  }
}

// Define FFI callback types
