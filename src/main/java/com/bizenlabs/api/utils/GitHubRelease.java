package com.bizenlabs.api.utils;

import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;

@RegisterReflectionForBinding
public class GitHubRelease {

    public String url;
    public String assets_url;
    public String upload_url;
    public String html_url;
    public int id;
    public String node_id;
    public String tag_name;
    public String target_commitish;
    public String name;
    public boolean draft;
    public boolean immutable;
    public boolean prerelease;
    public String created_at;
    public String published_at;
    public GitHubAsset[] assets;
    public String tarball_url;
    public String zipball_url;
    public String body;
}
