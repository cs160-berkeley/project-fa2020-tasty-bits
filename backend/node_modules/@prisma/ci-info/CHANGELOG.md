# 2.1.2 / 2020-01-21

- add types

# 2.1.1 / 2020-01-21

- add support for zeit now

# 2.1.0 / 2020-01-21

- add support for github actions

# 2.0.0

Breaking changes:

- Drop support for Node.js end-of-life versions: 0.10, 0.12, 4, 5, 7,
  and 9
- Team Foundation Server will now be detected as Azure Pipelines. The
  constant `ci.TFS` no longer exists - use `ci.AZURE_PIPELINES` instead
- Remove deprecated `ci.TDDIUM` constant - use `ci.SOLANDO` instead

New features:

- feat: support Azure Pipelines ([#23](https://github.com/watson/ci-info/pull/23))
- feat: support Netlify CI ([#26](https://github.com/watson/ci-info/pull/26))
- feat: support Bitbucket pipelines PR detection ([#27](https://github.com/watson/ci-info/pull/27))

# 1.6.0

- feat: add Sail CI support
- feat: add Buddy support
- feat: add Bitrise support
- feat: detect Jenkins PRs
- feat: detect Drone PRs

# 1.5.1

- fix: use full path to vendors.json

# 1.5.0

- feat: add dsari detection ([#15](https://github.com/watson/ci-info/pull/15))
- feat: add ci.isPR ([#16](https://github.com/watson/ci-info/pull/16))

# 1.4.0

- feat: add Cirrus CI detection ([#13](https://github.com/watson/ci-info/pull/13))
- feat: add Shippable CI detection ([#14](https://github.com/watson/ci-info/pull/14))

# 1.3.1

- chore: reduce npm package size by not including `.github` folder content ([#11](https://github.com/watson/ci-info/pull/11))

# 1.3.0

- feat: add support for Strider CD
- chore: deprecate vendor constant `TDDIUM` in favor of `SOLANO`
- docs: add missing vendor constant to docs

# 1.2.0

- feat: detect solano-ci ([#9](https://github.com/watson/ci-info/pull/9))

# 1.1.3

- fix: fix spelling of Hunson in `ci.name`

# 1.1.2

- fix: no more false positive matches for Jenkins

# 1.1.1

- docs: sort lists of CI servers in README.md
- docs: add missing AWS CodeBuild to the docs

# 1.1.0

- feat: add AWS CodeBuild to CI detection ([#2](https://github.com/watson/ci-info/pull/2))

# 1.0.1

- chore: reduce npm package size by using an `.npmignore` file ([#3](https://github.com/watson/ci-info/pull/3))

# 1.0.0

- Initial release
